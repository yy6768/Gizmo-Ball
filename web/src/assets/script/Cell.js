export class Cell {
    constructor(r, c) {
        this.r = r;
        this.c = c;

        // 行数和列数装换为坐标
        this.x = c + 0.5;
        this.y = r + 0.5;
    }
}