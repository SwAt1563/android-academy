from django.db import models
from account.models import UserAccount

# Create your models here.

class Trainee(models.Model):

    user = models.OneToOneField(UserAccount, on_delete=models.CASCADE, related_name='trainee')
    address = models.CharField(max_length=200)
    phone = models.CharField(max_length=20)
    enrolled_courses = models.ManyToManyField('course.Course', through='trainee.Enrollment')

    def __str__(self):
        return self.user.username

    @property
    def accepted_courses(self):
        return self.enrolled_courses.filter(enrollment__status='approved')


class Enrollment(models.Model):
    STATUS_CHOICES = (
        ('Pending', 'Pending'),
        ('Approved', 'Approved'),
        ('Rejected', 'Rejected'),
    )
    trainee = models.ForeignKey(Trainee, on_delete=models.CASCADE, related_name='enrollments')
    course = models.ForeignKey('course.Course', on_delete=models.CASCADE, related_name='enrollments')
    status = models.CharField(max_length=10, choices=STATUS_CHOICES, default='Pending')
    created = models.DateTimeField(auto_now_add=True)
    updated = models.DateTimeField(auto_now=True)

    class Meta:
        unique_together = ('trainee', 'course')

    def delete(self, *args, **kwargs):
        if self.status == 'Rejected':
            self.trainee.enrolled_courses.remove(self.course)
        super().delete(*args, **kwargs)

    def __str__(self):
        return f"{self.trainee} - {self.course.title}"
