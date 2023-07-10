from django.shortcuts import render
from .serializers import UserSignInSerializer, UserAccountSerializer
from rest_framework import generics
from rest_framework.response import Response
from owner.serializers import OwnerSerializer
from trainee.serializers import TraineeSerializer
from instructor.serializers import InstructorSerializer
from .models import UserAccount
from rest_framework.views import APIView
from rest_framework import status

# Create your views here.


class UserSignInView(generics.GenericAPIView):
    serializer_class = UserSignInSerializer

    def post(self, request, *args, **kwargs):
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)

        user = serializer.validated_data['user']

        if user.user_type == 'owner':
            user_data = OwnerSerializer(user.owner).data
        elif user.user_type == 'trainee':
            user_data = TraineeSerializer(user.trainee).data
        elif user.user_type == 'instructor':
            user_data = InstructorSerializer(user.instructor).data
        else:
            user_data = UserAccountSerializer(user).data

        return Response(user_data)



class UserAccountUpdateView(APIView):
    def patch(self, request, username):
        try:
            user = UserAccount.objects.get(username=username)
        except UserAccount.DoesNotExist:
            return Response({'error': 'UserAccount not found'}, status=status.HTTP_404_NOT_FOUND)

        serializer = UserAccountSerializer(user, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_200_OK)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


class UserTypeUsername(APIView):
    def get(self, request, username):
        user = UserAccount.objects.get(username=username)
        if not user:
            return Response({"detail": "User does not exist."}, status=status.HTTP_400_BAD_REQUEST)

        return Response({"user_type": user.user_type}, status=status.HTTP_200_OK)


class UserTypeEmail(APIView):
    def get(self, request, email):
        user = UserAccount.objects.get(email=email)
        if not user:
            return Response({"detail": "User does not exist."}, status=status.HTTP_400_BAD_REQUEST)

        return Response({"user_type": user.user_type}, status=status.HTTP_200_OK)
