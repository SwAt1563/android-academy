from rest_framework import serializers



from .models import Instructor
from account.serializers import UserAccountSerializer
from account.models import UserAccount


class InstructorSerializer(serializers.ModelSerializer):
    user = UserAccountSerializer()

    class Meta:
        model = Instructor
        fields = ['user', 'specialization', 'address', 'phone', 'degree']

    def create(self, validated_data):
        user_data = validated_data.pop('user')
        user = UserAccount.objects.create_user(**user_data, user_type='instructor')
        instructor = Instructor.objects.create(user=user, **validated_data)
        return instructor





