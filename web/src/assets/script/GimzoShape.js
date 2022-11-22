import { GimzoComponent } from "./GimzoComponent";
import { Cell } from "./Cell";

export class GimzoShape extends GimzoComponent {
    constructor(gameMap, c, r){
        super(gameMap, c, r);
        this.size = 1;
    }

    magnify(){
        let new_cells = [];
        let dx = [0, 1, 1];
        let dy = [1, 0, 1];
        let components = this.gameMap.components;
        for(let i in dx){
            if(components[[this.c + dx[i], this.r + dy[i]]] != null) {
                return false;
            }
            let cell = new Cell(this.r + dx[i], this.c + dy[i]);
            new_cells.push(cell);
            
            components[[this.c + dx[i], this.r + dy[i]]] = this;
        }
        this.cells = this.cells.concat(new_cells);
        this.size = 2;

        let socket = this.gameMap.store.state.layout.socket;
        socket.send("magnify " + this.id);
    }

    shrink(){
        let components = this.gameMap.components;
        for(let i = 1; i < this.cells.length; i++){
            let cell = this.cells[i];
            delete components[[cell.c,cell.r]];
        }
        this.cells.splice(1,3);
        this.size = 1;
        let socket = this.gameMap.store.state.layout.socket;
        socket.send("shrink " + this.id);
    }
}