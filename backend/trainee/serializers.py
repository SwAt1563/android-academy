from rest_framework import serializers



from .models import Trainee
from account.serializers import UserAccountSerializer
from account.models import UserAccount



class TraineeSerializer(serializers.ModelSerializer):
    user = UserAccountSerializer()

    class Meta:
        model = Trainee
        fields = ['user', 'address', 'phone']

    def create(self, validated_data):
        user_data = validated_data.pop('user')
        user = UserAccount.objects.create_user(**user_data, user_type='trainee')
        trainee = Trainee.objects.create(user=user, **validated_data)
        return trainee