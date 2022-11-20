import { Cell } from "./Cell";
import { GimzoObject } from "./GimzoObject";

export class Baffle extends GimzoObject{

    constructor(gameMap, c, r){
        super();
        console.log("create!");
        this.gameMap = gameMap;
        this.c = c;
        this.r = r;
        if(this.c < 10){
            this.limit = [0,10];
        } else {
            this.limit = [10,20];
        }
        this.dx = [1, -1];
        this.velocity = 10.0;


        this.cells = [];
        this.cells.push(new Cell(r ,c));
        this.cells.push(new Cell(r, c + 1));

        this.image = new Image();
        this.image.src = this.gameMap.store.state.icon.baffle_icon;
    }

    //绑定键盘
    add_listening_events(){
        const store = this.gameMap.store;
        const canvas = this.gameMap.ctx.canvas;
            
        if(store.state.layout.status === 'playing'){
            canvas.addEventListener("keydown", e=>{
            console.log(e);
                if(e.key ==='ArrowLeft'){
                    
                    console.log(this.c);
                    console.log(this.timedelta);
                    if(this.c - this.velocity * this.timedelta / 1000 >= this.limit[0]){
                        
                        this.c -= this.velocity * this.timedelta / 1000;
                    }
                } else if(e.key === 'ArrowRight'){
                    if(this.c + this.velocity * this.timedelta / 1000 < this.limit[1]){
                        this.c += this.velocity * this.timedelta / 1000;
                    }
                        
                }
            });
        }
    }

    start(){
        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], this);
        }
        this.add_listening_events();
    }

    update(){
        this.render();
    }
    

    on_destroy(){
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

        for(let cell of this.cells){
            this.gameMap.set_position([cell.c,cell.r], undefined);
        }

        let socket = this.gameMap.store.state.layout.socket;
        socket.send("delete Baffle " + this.id);
    }

    render(){
        const ctx = this.gameMap.ctx;
        const L = this.gameMap.L;
        ctx.drawImage(this.image,this.c * L ,this.r * L, L * 2 , L);
    }
}