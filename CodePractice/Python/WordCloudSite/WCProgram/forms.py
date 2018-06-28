# UploadFileModel에 대한 modelform을 생성한다.

from django import forms

from .models import UploadFileModel

class UploadFileForm(forms.ModelForm):
    file = forms.FileField(
        label = 'Select a file',
        help_text= 'max. 42 megabytes'
    )