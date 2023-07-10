
from django.contrib import admin
from django.urls import path
from . import views


app_name = 'owner'
urlpatterns = [
    path('signup/', views.OwnerSignUpView.as_view(), name='owner-signup'),
    path('profile/<str:username>/', views.OwnerProfileView.as_view(), name='owner-profile'),
    path('enrollment_status/', views.EnrollmentStatusView.as_view(), name='enrollment-status'),
    path('enrollment_list/', views.TraineeEnrollmentList.as_view(), name='trainee-enrollment-list'),


]


