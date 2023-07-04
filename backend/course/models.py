from django.db import models
from django.utils import timezone
from trainee.models import Trainee
from notification.models import Notification

# Create your models here.

class Course(models.Model):
    title = models.CharField(max_length=20, unique=True)
    topics = models.CharField(max_length=200)
    instructor = models.ForeignKey('instructor.Instructor', on_delete=models.CASCADE, related_name='courses')
    venue = models.CharField(max_length=200)
    created = models.DateTimeField(auto_now_add=True)
    updated = models.DateTimeField(auto_now=True)
    is_available = models.BooleanField(default=False)  # for trainee to enroll in course ( register in course )
    start_date = models.DateField()
    end_date = models.DateField()
    # The symmetrical=False parameter specifies that the relationship is not symmetrical, meaning that if course A is
    # a prerequisite for course B, it doesn't imply that course B is a prerequisite for course A.
    prerequisites = models.ManyToManyField('self', related_name='based_on_me', symmetrical=False, blank=True)


    def __str__(self):
        return self.title

    # for know the previous courses
    @property
    def is_finish(self):
        return timezone.now().date() >= self.end_date


    @property
    def trainees_count(self):
        return self.enrollments.filter(status='Approved').count()

    @property
    def trainees(self):
        return self.enrollments.filter(status='Approved').values_list('trainee__user__username', flat=True)

    def save(self, *args, **kwargs):
        is_available_before = self._state.fields_cache.get('is_available')
        super().save(*args, **kwargs)
        is_available_after = self.is_available

        if not is_available_before and is_available_after:
            # Get all trainees
            trainees = Trainee.objects.all()

            # Send notification to each trainee
            for trainee in trainees:
                notification = Notification.objects.create(
                    message='The course "{}" is now available.'.format(self.title), user=trainee.user)
                notification.save()

    class Meta:
        ordering = ['-start_date']



