from django.shortcuts import render
from rest_framework import generics
from .models import Instructor
from .serializers import InstructorSerializer
from course.models import Course
from course.serializers import CourseSerializer
# Create your views here.


class InstructorSignUpView(generics.CreateAPIView):
    queryset = Instructor.objects.all()
    serializer_class = InstructorSerializer

class InstructorProfileView(generics.RetrieveAPIView):
    queryset = Instructor.objects.all()
    serializer_class = InstructorSerializer
    lookup_field = 'user__username'
    lookup_url_kwarg = 'username'



class CurrentCoursesView(generics.ListAPIView):
    serializer_class = CourseSerializer

    def get_queryset(self):
        instructor_username = self.kwargs['instructor_username']
        return Course.objects.filter(instructor__user__username=instructor_username, is_finish=False)

class PreviousCoursesView(generics.ListAPIView):
    serializer_class = CourseSerializer

    def get_queryset(self):
        instructor_username = self.kwargs['instructor_username']
        return Course.objects.filter(instructor__user__username=instructor_username, is_finish=True)