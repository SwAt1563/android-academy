from django.shortcuts import render
from rest_framework import generics
from .models import Notification
from .serializers import NotificationSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from course.models import Course
from account.models import UserAccount
from django.shortcuts import get_object_or_404
from django.utils import timezone
from django.db import IntegrityError




# Create your views here.


class UserNotificationList(generics.ListAPIView):
    serializer_class = NotificationSerializer

    def get_queryset(self):
        username = self.kwargs['username']
        return Notification.objects.filter(user__username=username)



class CourseStartNotificationAPIView(APIView):
    def post(self, request, format=None):
        current_date = timezone.now().date()
        courses = Course.objects.filter(start_date=current_date)

        for course in courses:
            trainees = course.trainees
            instructor = course.instructor
            message = f"The course '{course.title}' is starting today."


            # Send notification to trainees
            for trainee in trainees:
                if not Notification.objects.filter(user=trainee, message=message).exists():
                    Notification.objects.create(message=message, user=trainee)

                # Send notification to instructor
                if not Notification.objects.filter(user=instructor.user, message=message).exists():
                    Notification.objects.create(message=message, user=instructor.user)


        return Response("Notifications sent.", status=status.HTTP_200_OK)