from django.contrib import admin

# Register your models here.

from .models import Trainee, Enrollment

admin.site.register(Trainee)
admin.site.register(Enrollment)