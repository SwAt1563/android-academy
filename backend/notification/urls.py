
from django.contrib import admin
from django.urls import path
from . import views


app_name = 'notification'
urlpatterns = [

    path('list/<str:username>/', views.UserNotificationList.as_view(), name='list'),
    path('start_date/', views.CourseStartNotificationAPIView.as_view(), name='course-start'),

]


