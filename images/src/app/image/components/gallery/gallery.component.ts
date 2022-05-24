import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Image} from "../../../image";
import {ImageService} from "../../services/image.service";
import {HttpErrorResponse} from "@angular/common/http";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.scss']
})
export class GalleryComponent implements OnInit {
  @Output() popup: EventEmitter<boolean>=new EventEmitter<boolean>();
  tagFormGroup: FormGroup;
  images: Image[]=[];
  tags: string[]=[];
  public load=false;
  public foundImages=false;
  openedImage: Image={
    url:'',
    timeAdded: '',
    service:'',
    width:0,
    height:0
  };

  constructor(private imageService: ImageService,private formBuilder: FormBuilder,private route:ActivatedRoute){
    this.tagFormGroup = this.formBuilder.group({
      name: ['',Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params=>
    {
      this.tags=this.route.snapshot.paramMap.get("tags")?.split(/,+/) ?? [];
      this.getImagesByTag(this.tags[0]);
    })
    this.getImages();
  }

  public getImagesByTag(tagName: string){
    this.imageService.getImagesByTagName(tagName).subscribe(
      (response: Image[]) => {
        this.images = response;
      }
    );
    this.closePopUp();
  }


  public getImagesByTagName(){
    if (!this.tagFormGroup.valid) {
      alert('The form is not valid!');
      return;
    }
    const name=this.tagFormGroup.get('name')?.value;
    this.imageService.getImagesByTagName(name).subscribe(
      (response: Image[]) => {
        this.images = response;
      },
      (error: HttpErrorResponse) => {
        if(error.message.endsWith("400 OK")){
          alert("There is no image tagged with "+name+"!");
        }
      }
    );
  }

  public closePopUp(): void{
    this.load=false;
  }

  public openPopUp(image: Image): void{
    this.load=true;
    this.openedImage=image;
  }

  public getImages(): void {
    this.imageService.getImages().subscribe(
      (response: Image[]) => {this.images = response;},
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
