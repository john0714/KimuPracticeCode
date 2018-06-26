"""
WSGI config for WordCloudSite project.

Web Server Gateway Interface
파이썬에서 웹 서버와 웹 애플리케이션간의 동작을 중계해주는 인터페이스 표준 웹 클라이언트의 HTTP 프로토콜
요청을 Python Call로 변환하기 위한 매핑관계로 WSGI을 표준으로 사용. uWSGI는 WSGI 표준의 구현

It exposes the WSGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/2.0/howto/deployment/wsgi/
"""

import os

from django.core.wsgi import get_wsgi_application

os.environ.setdefault("DJANGO_SETTINGS_MODULE", "WordCloudSite.settings")

application = get_wsgi_application()
