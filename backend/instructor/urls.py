
from django.contrib import admin
from django.urls import path
from . import views


app_name = 'instructor'
urlpatterns = [
    path('signup/', views.InstructorSignUpView.as_view(), name='instructor-signup'),
    path('profile/<str:username>/', views.InstructorProfileView.as_view(), name='instructor-profile'),
    path('current_courses/<str:instructor_username>/', views.CurrentCoursesView.as_view(), name='current-courses'),
    path('previous_courses/<str:instructor_username>/', views.PreviousCoursesView.as_view(), name='previous-courses'),
    path('list/', views.InstructorsListView.as_view(), name='instructors-list'),

]


