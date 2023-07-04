from django.db import models
from account.models import UserAccount
# Create your models here.

class Instructor(models.Model):
    DEGREE_CHOICES = (
        ('BSc', 'BSc'),
        ('MSc', 'MSc'),
        ('PhD', 'PhD'),
    )
    user = models.OneToOneField(UserAccount, on_delete=models.CASCADE, related_name='instructor')
    specialization = models.CharField(max_length=100)
    address = models.CharField(max_length=200)
    phone = models.CharField(max_length=20)
    degree = models.CharField(max_length=3, choices=DEGREE_CHOICES)


    def __str__(self):
        return self.user.username