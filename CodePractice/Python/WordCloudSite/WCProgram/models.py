from django.db import models

# title과 upload할 file에 대한 정보를 갖는 model
# file은 존재하지 않는 경우도 있으므로 null=True로 설정함
class UploadFileModel(models.Model):
    # models.FileField : 파일 저장을 지원하는 모델 필드
    # models.ImageField : 이미지 저장을 지원하는 모델 필드(FileField 상속)

    #title = models.TextField(default='')
    file = models.FileField(null=True)
    #photo = models.ImageField(blank=True)