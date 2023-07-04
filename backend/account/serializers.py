from rest_framework import serializers
from .models import UserAccount




class UserAccountSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserAccount
        fields = ['username', 'first_name', 'last_name', 'password', 'email', 'user_type']
        extra_kwargs = {'password': {'write_only': True}, 'user_type': {'read_only': True}}


    def update(self, instance, validated_data):
        # Update only the fields present in the validated_data
        for attr, value in validated_data.items():
            setattr(instance, attr, value)
        instance.save()

        return instance


class UserSignInSerializer(serializers.Serializer):
    email = serializers.EmailField()
    password = serializers.CharField()

    def validate(self, attrs):
        email = attrs.get('email')
        password = attrs.get('password')

        if email and password:
            user = UserAccount.objects.filter(email=email).first()

            if user:
                if user.check_password(password):
                    attrs['user'] = user
                else:
                    raise serializers.ValidationError('Incorrect password.')
            else:
                raise serializers.ValidationError('User does not exist.')
        else:
            raise serializers.ValidationError('Please provide both email and password.')

        return attrs
