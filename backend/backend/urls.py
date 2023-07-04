
from django.contrib import admin
from django.urls import path, include
from django.conf import settings
from django.conf.urls.static import static
from .dummy_data import CreateDummyDataView

urlpatterns = [
    path('admin/', admin.site.urls),
    path('account/', include('account.urls', namespace='account')),
    path('owner/', include('owner.urls', namespace='owner')),
    path('instructor/', include('instructor.urls', namespace='instructor')),
    path('trainee/', include('trainee.urls', namespace='trainee')),
    path('course/', include('course.urls', namespace='course')),
    path('notification/', include('notification.urls', namespace='notification')),

    path('dummy_data/', CreateDummyDataView.as_view(), name='dummy-data'),



]


if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
    urlpatterns += static(settings.STATIC_URL, document_root=settings.STATIC_ROOT)