import datetime
from django.db import models
from django.utils import timezone

class Question(models.Model):
    question_text = models.CharField(max_length=200)
    pub_date = models.DateTimeField("date published")
    def __str__(self):
        return self.question_text
    def was_published_recently(self):
        return self.pub_date >= timezone.now() - datetime.timedelta(days=1)

class Choice(models.Model):
    question = models.ForeignKey(Question, on_delete=models.CASCADE)
    choice_text = models.CharField(max_length=200)
    votes = models.IntegerField(default=0)
    def __str__(self):
        return self.choice_text
    
class User(models.Model):
    GENDER_CHOICES = [
        ('M', 'Male'),
        ('F', 'Female'),
        ('O', 'Other'),
    ]
    name = models.CharField(max_length=100)
    user_name = models.CharField(max_length=50, unique=True)
    user_email = models.EmailField(max_length=254, unique=True)
    user_password = models.CharField(max_length=6)
    user_gender = models.CharField(max_length=1, choices=GENDER_CHOICES)
    id = models.AutoField(primary_key=True)
    def __str__(self):
        return self.user_name
    class Meta:
        db_table='User'
    