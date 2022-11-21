import { GimzoComponent } from "./GimzoComponent";
import { Cell } from "./Cell"
export class Baffle extends GimzoComponent{

    constructor(gameMap, c, r){
        super(gameMap, c, r);
        
        if(this.c < 10){
            this.limit = [0,10];
        } else {
            this.limit = [10,20];
        }

        this.cells.push(new Cell(r, c + 1));
        this.dx = [1, -1];
        this.velocity = 10.0;

        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.baffle_icon;
    }

    //绑定键盘
    add_listening_events(){
        const store = this.gameMap.store;
        const canvas = this.gameMap.ctx.canvas;
        canvas.addEventListener("keydown", e=>{
            console.log(e);
            if(store.state.layout.status === 'game'){
                if(e.key ==='ArrowLeft'){
                    if(this.c - this.velocity * this.timedelta / 1000 >= this.limit[0]){    
                        this.c -= this.velocity * this.timedelta / 1000;
                    }
                } else if(e.key === 'ArrowRight'){
                    if(this.c + this.velocity * this.timedelta / 1000 < this.limit[1]){
                        this.c += this.velocity * this.timedelta / 1000;
                    }
                }
            }
        });    
        
    }

    start(){
        super.start();
        this.add_listening_events();
    }

    update(){
        this.render();
    }
    

    on_destroy(){
        super.on_destroy();

        const canvas = this.gameMap.ctx.canvas;
        canvas.removeEventListener("keydown",e=>{
            if(e ==='ArrowLeft'){
                if(this.c - this.velocity * this.timedelta >= this.limit[0]) 
                    this.c -= this.velocity * this.timedelta;
            } else if(e === 'ArrowRight'){
                if(this.c + this.velocity * this.timedelta < this.timedelta)
                    this.c += this.velocity * this.timedelta;
            }
        });

    }

    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image,this.c * L ,this.r * L, L * 2 , L);
    }
}