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
            users_data = [
                {'username': 'user1', 'first_name': 'John', 'last_name': 'Doe', 'email': 'user1@example.com',
                 'password': '1', 'user_type': 'owner'},
                {'username': 'user2', 'first_name': 'Jane', 'last_name': 'Smith', 'email': 'user2@example.com',
                 'password': '1', 'user_type': 'owner'},
                {'username': 'user3', 'first_name': 'Alice', 'last_name': 'Johnson', 'email': 'user3@example.com',
                 'password': '1', 'user_type': 'instructor'},
                {'username': 'user4', 'first_name': 'Bob', 'last_name': 'Brown', 'email': 'user4@example.com',
                 'password': '1', 'user_type': 'instructor'},
                {'username': 'user5', 'first_name': 'Emily', 'last_name': 'Davis', 'email': 'user5@example.com',
                 'password': '1', 'user_type': 'trainee'},
                {'username': 'user6', 'first_name': 'Michael', 'last_name': 'Wilson', 'email': 'user6@example.com',
                 'password': '1', 'user_type': 'trainee'},
            ]

            # Create user accounts
            for user in users_data:
                UserAccount.objects.create_user(**user)

            user1 = UserAccount.objects.get(username='user1')
            user2 = UserAccount.objects.get(username='user2')
            user3 = UserAccount.objects.get(username='user3')
            user4 = UserAccount.objects.get(username='user4')
            user5 = UserAccount.objects.get(username='user5')
            user6 = UserAccount.objects.get(username='user6')



            # Create owners
            owner1 = Owner.objects.create(user=user1)
            owner2 = Owner.objects.create(user=user2)

            # Create instructors
            instructor1 = Instructor.objects.create(
                user=user3,
                specialization='CSE',
                address='Tulkarm',
                phone='0568187266',
                degree='BSc'
            )
            instructor2 = Instructor.objects.create(
                user=user4,
                specialization='CE',
                address='Ramallah',
                phone='0599675878',
                degree='MSc'
            )

            # Create trainees
            trainee1 = Trainee.objects.create(
                user=user5,
                address='Jenin',
                phone='05996759789'
            )
            trainee2 = Trainee.objects.create(
                user=user6,
                address='Jerusalem',
                phone='05668487598'
            )

            # Create courses
            course1 = Course.objects.create(
                title='Course 1',
                topics='Artificial Intelligence',
                instructor=instructor1,
                venue='Birzeit',
                start_date='2023-07-01',
                end_date='2023-07-10'
            )
            course2 = Course.objects.create(
                title='Course 2',
                topics='Machine Learning',
                instructor=instructor2,
                venue='Birzeit',
                start_date='2023-08-01',
                end_date='2023-08-10'
            )

            # Add prerequisites to courses
            course2.prerequisites.add(course1)

            # Enroll trainees in courses
            enrollment1 = Enrollment.objects.create(trainee=trainee1, course=course1, status='Pending')
            enrollment2 = Enrollment.objects.create(trainee=trainee2, course=course1, status='Approved')
            enrollment3 = Enrollment.objects.create(trainee=trainee2, course=course2, status='Pending')

            return Response({"detail": "Dummy data created successfully."}, status=status.HTTP_201_CREATED)
        except Exception as e:
            return Response({"detail": str(e)}, status=status.HTTP_500_INTERNAL_SERVER_ERROR)
