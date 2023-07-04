
from django.contrib import admin
from django.urls import path
from . import views


app_name = 'course'
urlpatterns = [


    path('list/', views.CourseListView.as_view(), name='list'),
    path('registration_list/', views.RegistrationCoursesView.as_view(), name='registration'),

    path('detail/<str:title>/', views.CourseDetailView.as_view(), name='detail'),
    path('create/', views.CourseCreateView.as_view(), name='create'),
    path('update/<str:title>/', views.CourseUpdateView.as_view(), name='update'),
    path('delete/<str:title>/', views.CourseDeleteView.as_view(), name='delete'),

]


