
from django.contrib import admin
from django.urls import path
from . import views


app_name = 'trainee'
urlpatterns = [

    path('signup/', views.TraineeSignUpView.as_view(), name='trainee-signup'),
    path('profile/<str:username>/', views.TraineeProfileView.as_view(), name='trainee-profile'),
    path('enroll/', views.CourseEnrollmentView.as_view(), name='course-enrollment'),
    path('courses/<str:username>/', views.TraineeCoursesView.as_view(), name='trainee-courses'),

]


