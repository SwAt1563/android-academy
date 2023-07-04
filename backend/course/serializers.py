from rest_framework import serializers



from .models import Course
from instructor.models import Instructor


class CourseSerializer(serializers.ModelSerializer):
    instructor = serializers.CharField(source='instructor.user.username')
    start_date = serializers.DateField(format='%Y-%m-%d')
    end_date = serializers.DateField(format='%Y-%m-%d')
    prerequisites = serializers.SerializerMethodField()
    trainees_count = serializers.IntegerField(read_only=True)
    trainees = serializers.ListField(child=serializers.CharField(), read_only=True)
    is_finish = serializers.ReadOnlyField()
    is_available = serializers.ReadOnlyField()

    def get_prerequisites(self, obj):
        prerequisites = obj.prerequisites.all()
        return [prerequisite.title for prerequisite in prerequisites]


    class Meta:
        model = Course
        fields = ['title', 'topics', 'instructor', 'venue', 'prerequisites', 'start_date', 'end_date',  'trainees_count',
                  'trainees', 'is_finish', 'is_available']


    def to_internal_value(self, data):
        ret = super().to_internal_value(data)
        start_date_str = data.get('start_date')
        end_date_str = data.get('end_date')
        prerequisites_str = data.get('prerequisites')
        instructor_username = data.get('instructor')
        if start_date_str:
            ret['start_date'] = serializers.DateField().to_internal_value(start_date_str)
        if end_date_str:
            ret['end_date'] = serializers.DateField().to_internal_value(end_date_str)
        if prerequisites_str:
            ret['prerequisites'] = [title.strip() for title in prerequisites_str.split(',')]
        if instructor_username:
            ret['instructor'] = instructor_username
        return ret

    def create(self, validated_data):
        prerequisites_data = validated_data.pop('prerequisites', [])
        instructor_username = validated_data.pop('instructor')
        instructor = Instructor.objects.get(user__username=instructor_username)
        course = Course.objects.create(instructor=instructor, **validated_data)

        prerequisites = Course.objects.filter(title__in=prerequisites_data)
        course.prerequisites.set(prerequisites)
        return course

    def update(self, instance, validated_data):
        # Update only the fields present in the validated_data
        for attr, value in validated_data.items():
            setattr(instance, attr, value)
        instance.save()

        return instance



