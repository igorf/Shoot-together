function Target (canvas) {
    this.canvas = canvas;
    this.circles = [];
    this.ctx = this.canvas.getContext('2d');
    this.centerX = canvas.width / 2;
    this.centerY = canvas.height / 2;
    this.radius = Math.ceil(Math.min(this.centerX, this.centerY));
    this.ringScaleFactor = 1;
    this.showBackground = false;
}

Target.prototype = {
    TARGET_TO_CANVAS_SCALE: 0.85,

    drawBackground: function() {
        this.ctx.lineWidth = 1;
        this.ctx.strokeStyle = "#000000";
        this.ctx.rect(0, 0, this.radius * 2, this.radius * 2);
        this.ctx.stroke();
    },

    draw: function () {
        if (this.showBackground) {
            this.drawBackground();
        }
        for (let circle of this.circles) {
            circle.draw();
        }
    },

    addCircle: function (circle) {
        circle.target = this;
        this.circles.push(circle);
        this.arrange()
    },

    arrange: function() {
        this.circles = this.circles.sort(function (a, b) {
            if (a.diameter > b.diameter) return -1;
            if (a.diameter < b.diameter) return 1;
            return 0;
        });
        this.updateScale()
    },

    updateScale: function () {
        if (this.circles.length > 0 && this.circles[0].diameter > 0) {
            this.ringScaleFactor = (2 * this.radius * this.TARGET_TO_CANVAS_SCALE) / this.circles[0].diameter;
        }
    }
};

function TargetCircle (diameter, color, value) {
    this.diameter = diameter;
    this.color = new TargetRingColor(color);
    this.value = value;
}

TargetCircle.prototype = {
    draw: function () {
        let context = this.target.ctx;
        context.beginPath();
        context.arc(this.target.centerX, this.target.centerY, (this.diameter / 2 * this.target.ringScaleFactor), 0, 2 * Math.PI, false);
        context.fillStyle = this.color.fill;
        context.fill();
        context.lineWidth = 1;
        context.strokeStyle = this.color.border;
        context.stroke();
    }
};

function TargetRingColor(colorConst) {
    this.border = "";
    this.fill = "";
    this.init(colorConst)
}

TargetRingColor.prototype = {
    ringColors: {"BLACK": "#ffffcc", "WHITE": "#000000"},
    fillColors: {"BLACK": "#000000", "WHITE": "#ffffcc"},

    init: function (colorConst) {
        this.border = this.ringColors[colorConst];
        this.fill = this.fillColors[colorConst];
    }
};