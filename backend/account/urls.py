
from django.contrib import admin
from django.urls import path
from . import views


app_name = 'account'
urlpatterns = [
    path('signin/', views.UserSignInView.as_view(), name='user-signin'),
    path('update/<str:username>/', views.UserAccountUpdateView.as_view(), name='user-update'),

]


