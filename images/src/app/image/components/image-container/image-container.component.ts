import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ImageService} from "../../services/image.service";
import {StringUtils} from "turbocommons-ts";
import {Image} from "../../../image";
import {HttpErrorResponse} from "@angular/common/http";
import {ImageTag} from "../../../imageTag";
import {map, Observable} from "rxjs";

@Component({
  selector: 'app-image-container',
  templateUrl: './image-container.component.html',
  styleUrls: ['./image-container.component.scss']
})
export class ImageContainerComponent implements OnInit {
  imageFormGroup: FormGroup;
  images: Image[] = [];
  imagesByTagName:Image[]=[];
  lastAddedImage: Image = {
    url: '',
    service: '',
    timeAdded: '',
    width: 0,
    height: 0
  };
  imageById: Image = {
    url: '',
    service: '',
    timeAdded: '',
    width: 0,
    height: 0
  };
  imageTags: ImageTag[] = [];
  public load = false;

  constructor(private imageService: ImageService, private formBuilder: FormBuilder) {
    this.imageFormGroup = this.formBuilder.group({
      url: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.getImages();
  }


  public getImages(): void {
    this.imageService.getImages().subscribe(
      (response: Image[]) => {
        this.images = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getImage(): void {
    this.imageService.getImage(this.imageFormGroup.get('imageId')?.value).subscribe((result) => {
      this.imageById = result;
    });

  }

  public addImage() {
    if (!this.imageFormGroup.valid) {
      alert('The form is not valid!');
      return;
    }

    if (!StringUtils.isUrl(this.imageFormGroup.get('url')?.value)) {
      alert('The given url is not valid!');
      return;
    }

    const imageToCreate: Image = {
      url: this.imageFormGroup.get('url')?.value,
      timeAdded: '',
      service: '',
      height: 0,
      width: 0
    };

    this.imageService.createImage(imageToCreate).subscribe(() => {
      this.imageService.getImages().subscribe(
        (response: Image[]) => {
          this.images = response;
          this.lastAddedImage = this.images[this.images.length - 1];
          this.load = true;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    });

  }

  public getImageByUrl(url: string): void {
    for (let image of this.images) {
      if (image.url == url) {
        this.lastAddedImage.url = image.url;
        break;
      }
    }
  }


  public closePopUp(): void {
    this.load = false;
  }

  public openPopUp(): void {
    this.load = true;
  }
}

