# Generated by Django 4.2.2 on 2023-07-09 17:36

from django.conf import settings
from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('notification', '0001_initial'),
    ]

    operations = [
        migrations.AlterUniqueTogether(
            name='notification',
            unique_together={('message', 'user')},
        ),
    ]