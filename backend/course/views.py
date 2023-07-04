from django.shortcuts import render

# Create your views here.

from rest_framework import generics
from .models import Course
from .serializers import CourseSerializer
from notification.models import Notification


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

class CourseUpdateView(generics.UpdateAPIView):
    queryset = Course.objects.all()
    serializer_class = CourseSerializer
    lookup_field = 'title'

    def perform_update(self, serializer):
        instance = serializer.save()

        # Check if the course has been updated
        if serializer.instance._state.adding or serializer.instance._state.modified_fields:
            # Get the approved trainees of the course
            approved_trainees = instance.enrollments.filter(status='Approved').values_list('trainee__user', flat=True)

            # Create notifications for the approved trainees
            for user in approved_trainees:
                message = f"The course {instance.title} has been updated."
                Notification.objects.create(message=message, user=user)


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
            Notification.objects.create(message=message, user=user)

        instance.delete()