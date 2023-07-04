from rest_framework import serializers



from .models import Owner
from account.serializers import UserAccountSerializer
from account.models import UserAccount


class OwnerSerializer(serializers.ModelSerializer):
    user = UserAccountSerializer()

    class Meta:
        model = Owner
        fields = ['user']

    def create(self, validated_data):
        user_data = validated_data.pop('user')
        user = UserAccount.objects.create_user(**user_data, user_type='owner')
        owner = Owner.objects.create(user=user, **validated_data)
        return owner