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
        courses = Course.objects.filter(instructor__user__username=instructor_username)
        not_finished_courses = [course for course in courses if not course.is_finish]
        return not_finished_courses

class PreviousCoursesView(generics.ListAPIView):
    serializer_class = CourseSerializer

    def get_queryset(self):
        instructor_username = self.kwargs['instructor_username']
        courses = Course.objects.filter(instructor__user__username=instructor_username)
        finished_courses = [course for course in courses if course.is_finish]
        return finished_courses