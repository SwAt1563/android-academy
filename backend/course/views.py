from django.shortcuts import render

# Create your views here.

from rest_framework import generics
from .models import Course
from .serializers import CourseSerializer
from notification.models import Notification
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from account.models import UserAccount

class CourseListView(generics.ListAPIView):
    queryset = Course.objects.all()
    serializer_class = CourseSerializer

class RegistrationCoursesView(generics.ListAPIView):
    queryset = Course.objects.filter(is_available=True)
    serializer_class = CourseSerializer

class CourseDetailView(generics.RetrieveAPIView):
    queryset = Course.objects.all()
    serializer_class = CourseSerializer
    lookup_field = 'title'

class CourseCreateView(generics.CreateAPIView):
    queryset = Course.objects.all()
    serializer_class = CourseSerializer


class CourseUpdateView(APIView):
    def patch(self, request, title):
        try:
            course = Course.objects.get(title=title)
        except Course.DoesNotExist:
            return Response({'error': 'Course not found'}, status=status.HTTP_404_NOT_FOUND)

        serializer = CourseSerializer(course, data=request.data, partial=True)
        if serializer.is_valid():
            instance = serializer.save()

            # Check if the course has been updated
            if serializer.instance != serializer.data:
                # Get the approved trainees of the course
                approved_trainees = instance.enrollments.filter(status='Approved').values_list('trainee__user', flat=True)

                # Create notifications for the approved trainees
                for user in approved_trainees:
                    message = f"The course {instance.title} has been updated."
                    user = UserAccount.objects.get(id=user)
                    Notification.objects.create(message=message, user=user)

            return Response(serializer.data, status=status.HTTP_200_OK)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class CourseDeleteView(generics.DestroyAPIView):
    queryset = Course.objects.all()
    serializer_class = CourseSerializer
    lookup_field = 'title'

    def perform_destroy(self, instance):
        # Get the approved trainees of the course
        approved_trainees = instance.enrollments.all().values_list('trainee__user', flat=True)

        # Create notifications for the approved trainees
        for user in approved_trainees:
            message = f"The course {instance.title} has been deleted."

            # Retrieve the UserAccount instance
            user_account = UserAccount.objects.get(id=user)

            Notification.objects.create(message=message, user=user_account)

        instance.delete()