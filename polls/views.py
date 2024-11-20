from django.shortcuts import render
from django.http import HttpResponse

from django.contrib.auth.models import User
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt
import json
from django.contrib.auth import authenticate, login

@csrf_exempt
def register(request):
    if request.method == 'POST':
        data = json. loads(request.body.decode('utf-8'))
        username = data.get( 'username' )
        password = data.get( 'password' )
        email = data.get('email')
        name = data.get('userid' )
        gender = data.get('gender')

        # 회원가입 처리
        if not User.objects.filter(user_name=username).exists():
            user = User.objects.create_user(name = name, user_name = username, user_password=password, user_email=email, user_gender=gender)
            return JsonResponse({"message": "User registered successfully"},status = 201)    
        else:
            return JsonResponse({"error": "Username already exists"}, status=400)

    return JsonResponse({"error": "Invalid request method"}, status=400)

@csrf_exempt
def login_user(request):
    if request.method == 'POST':
        data = json. loads(request.body.decode('utf-8'))
        username = data.get( 'username' )
        password = data.get( 'password' )

    # 로그인 처리
        user = authenticate(username=username, password=password)
        if user is not None:
            login(request, user)
            return JsonResponse( {"message": "Login successful"}, status=200)
        else:
            return JsonResponse({"error": "Invalid credentials"}, status=400)

    return JsonResponse({"error": "Invalid request method"}, status=400)


def index(request):
    return HttpResponse("Hello, world. You're at the polls index.")

