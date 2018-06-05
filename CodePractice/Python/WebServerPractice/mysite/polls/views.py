from django.shortcuts import render
from django.http import HttpResponse

# view화면
def index(request):
    return HttpResponse("Hello, world. You're at the polls index.")