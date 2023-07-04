from django.shortcuts import render
from rest_framework import generics
from .models import Trainee, Enrollment
from .serializers import TraineeSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from course.models import Course
from course.serializers import CourseSerializer
from rest_framework.exceptions import NotFound


# Create your views here.



class TraineeSignUpView(generics.CreateAPIView):
    queryset = Trainee.objects.all()
    serializer_class = TraineeSerializer


class TraineeProfileView(generics.RetrieveAPIView):
    queryset = Trainee.objects.all()
    serializer_class = TraineeSerializer
    lookup_field = 'user__username'
    lookup_url_kwarg = 'username'



class CourseEnrollmentView(APIView):
    def post(self, request):
        trainee_username = request.data.get('trainee_username')
        course_title = request.data.get('course_title')

        # Retrieve the trainee and course objects based on the provided username and title
        try:
            trainee = Trainee.objects.get(user__username=trainee_username)
            course = Course.objects.get(title=course_title)
        except Trainee.DoesNotExist:
            return Response({"detail": "Trainee does not exist."}, status=status.HTTP_400_BAD_REQUEST)
        except Course.DoesNotExist:
            return Response({"detail": "Course does not exist."}, status=status.HTTP_400_BAD_REQUEST)

        # Check if the trainee already has a course within the specified date range
        has_course = trainee.enrollments.filter(course__start_date__lte=course.end_date,
                                                course__end_date__gte=course.start_date).exists()
        if has_course:
            return Response({"detail": "Trainee already has a course within the specified date range."},
                            status=status.HTTP_400_BAD_REQUEST)


        # Check if the trainee has completed the prerequisites of the new course
        prerequisites = course.prerequisites.all()
        if prerequisites.exists() and \
                prerequisites.count() != trainee.enrolled_courses.filter(id__in=prerequisites).count():
            return Response({"detail": "Trainee has not completed the prerequisites for the course."},
                            status=status.HTTP_400_BAD_REQUEST)


        # Create a new enrollment if the trainee is eligible
        enrollment = Enrollment(trainee=trainee, course=course, status='Pending')
        enrollment.save()

        return Response({"detail": "Enrollment successful."}, status=status.HTTP_201_CREATED)


class TraineeCoursesView(generics.ListAPIView):
    serializer_class = CourseSerializer

    def get_queryset(self):
        trainee_username = self.kwargs['username']
        try:
            trainee = Trainee.objects.get(user__username=trainee_username)
            return trainee.enrolled_courses.filter(enrollments__status='Approved')  # NOT CARE is_finish=False
        except Trainee.DoesNotExist:
            raise NotFound('Trainee not found.')
