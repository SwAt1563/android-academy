from django.db import models
from account.models import UserAccount
# Create your models here.

class  Notification(models.Model):
    message = models.CharField(max_length=200)
    user = models.ForeignKey(UserAccount, on_delete=models.CASCADE, related_name='notifications')
    created = models.DateTimeField(auto_now_add=True)


    class Meta:
        ordering = ['-created']
    def __str__(self):
        return self.message
