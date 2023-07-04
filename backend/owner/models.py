from django.db import models
from account.models import UserAccount

# Create your models here.

class Owner(models.Model):
    user = models.OneToOneField(UserAccount, on_delete=models.CASCADE, related_name='owner')

    def __str__(self):
        return self.user.username