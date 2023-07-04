from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from account.models import UserAccount
from instructor.models import Instructor
from trainee.models import Trainee, Enrollment
from course.models import Course
from owner.models import Owner
from notification.models import Notification

class CreateDummyDataView(APIView):
    def post(self, request):
        try:
            # Create user accounts
            user1 = UserAccount.objects.create(username='user1')
            user2 = UserAccount.objects.create(username='user2')
            user3 = UserAccount.objects.create(username='user3')

            # Create owners
            owner1 = Owner.objects.create(user=user1)
            owner2 = Owner.objects.create(user=user2)

            # Create instructors
            instructor1 = Instructor.objects.create(
                user=user1,
                specialization='Specialization 1',
                address='Address 1',
                phone='Phone 1',
                degree='BSc'
            )
            instructor2 = Instructor.objects.create(
                user=user2,
                specialization='Specialization 2',
                address='Address 2',
                phone='Phone 2',
                degree='MSc'
            )

            # Create trainees
            trainee1 = Trainee.objects.create(
                user=user2,
                address='Address 2',
                phone='Phone 2'
            )
            trainee2 = Trainee.objects.create(
                user=user3,
                address='Address 3',
                phone='Phone 3'
            )

            # Create courses
            course1 = Course.objects.create(
                title='Course 1',
                topics='Topic 1',
                instructor=instructor1,
                venue='Venue 1',
                start_date='2023-07-01',
                end_date='2023-07-10'
            )
            course2 = Course.objects.create(
                title='Course 2',
                topics='Topic 2',
                instructor=instructor2,
                venue='Venue 2',
                start_date='2023-08-01',
                end_date='2023-08-10'
            )

            # Add prerequisites to courses
            course2.prerequisites.add(course1)

            # Enroll trainees in courses
            enrollment1 = Enrollment.objects.create(trainee=trainee1, course=course1, status='Approved')
            enrollment2 = Enrollment.objects.create(trainee=trainee2, course=course2, status='Approved')

            return Response({"detail": "Dummy data created successfully."}, status=status.HTTP_201_CREATED)
        except Exception as e:
            return Response({"detail": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)
