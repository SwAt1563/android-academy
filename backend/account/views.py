from django.shortcuts import render
from .serializers import UserSignInSerializer, UserAccountSerializer
from rest_framework import generics
from rest_framework.response import Response
from owner.serializers import OwnerSerializer
from trainee.serializers import TraineeSerializer
from instructor.serializers import InstructorSerializer
from .models import UserAccount

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

# update user account
class UserAccountUpdateView(generics.UpdateAPIView):
    queryset = UserAccount.objects.all()
    serializer_class = UserAccountSerializer
    lookup_field = 'username'
    lookup_url_kwarg = 'username'  # the name of the url parameter ( the default is equal to lookup_field )