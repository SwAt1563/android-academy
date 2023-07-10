from django.shortcuts import render
from rest_framework import generics
from .models import Owner
from .serializers import OwnerSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from trainee.models import Trainee, Enrollment
from course.models import Course
from account.models import UserAccount
from course.serializers import CourseSerializer

# Create your views here.


class OwnerSignUpView(generics.CreateAPIView):
    queryset = Owner.objects.all()
    serializer_class = OwnerSerializer

class OwnerProfileView(generics.RetrieveAPIView):
    queryset = Owner.objects.all()
    serializer_class = OwnerSerializer
    lookup_field = 'user__username'
    lookup_url_kwarg = 'username'



class EnrollmentStatusView(APIView):
    def post(self, request):
        trainee_username = request.data.get('trainee_username')
        course_title = request.data.get('course_title')
        enrollment_status = request.data.get('enrollment_status')

        # Retrieve the trainee and course objects based on the provided username and title
        try:
            trainee = Trainee.objects.get(user__username=trainee_username)
            course = Course.objects.get(title=course_title)
        except Trainee.DoesNotExist:
            return Response({"detail": "Trainee does not exist."}, status=status.HTTP_400_BAD_REQUEST)
        except Course.DoesNotExist:
            return Response({"detail": "Course does not exist."}, status=status.HTTP_400_BAD_REQUEST)

        enrollment = Enrollment.objects.get(trainee=trainee, course=course, status='Pending')
        if not enrollment:
            return Response({"detail": "The enrollment not exists"}, status=status.HTTP_400_BAD_REQUEST)

        enrollment.status = enrollment_status
        enrollment.save()

        if enrollment_status == 'Rejected':
            enrollment.delete()
            # return Delete status response
            return Response({"detail": "Enrollment Rejected."}, status=status.HTTP_204_NO_CONTENT)


        return Response({"detail": "Enrollment successful Approved."}, status=status.HTTP_201_CREATED)





class TraineeEnrollmentList(APIView):
    def get(self, request):
        enrollments = Enrollment.objects.filter(status='Pending')
        response = [f"{enrollment.course.title},{enrollment.trainee}" for enrollment in enrollments]
        return Response(response, status=status.HTTP_200_OK)





