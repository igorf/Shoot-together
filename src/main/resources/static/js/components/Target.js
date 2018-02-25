function Target (canvas, width, height) {
    this.canvas = new Konva.Stage({
        container: canvas,
        width: width,
        height: height
    });

    this.bgLayer = new Konva.Layer();
    this.shotLayer = new Konva.Layer();
    this.dragLayer = new Konva.Layer();
    this.textLayer = new Konva.Layer();

    this.width = width;
    this.height = height;
    this.centerX = width / 2;
    this.centerY = height / 2;
    this.radius = Math.ceil(Math.min(this.centerX, this.centerY));
    this.ringScaleFactor = 1;
    this.showBackground = false;
    this.minRing = 0;
    this.editable = false;

    this.circles = [];
    this.shots = [];

    var stage = this.canvas;
    var shotLayer = this.shotLayer;
    var dragLayer = this.dragLayer;
    var target = this;

    this.shotDenominationText = new Konva.Text({
        x: 10,
        y: 10,
        fontFamily: 'Courier',
        fontSize: 24,
        text: '',
        fill: 'black'
    });

    this.targetTotalText = new Konva.Text({
        width: stage.getWidth() / 2 - 10,
        x: stage.getWidth() / 2,
        y: 10,
        fontFamily: 'Courier',
        fontSize: 24,
        text: '',
        fill: 'black',
        align: 'right'
    });

    this.canvas.on('dragstart', function(evt) {
        let shape = evt.target;
        shape.moveTo(dragLayer);
        stage.draw();
    });

    this.canvas.on('dragend', function(evt) {
        let shape = evt.target;
        shape.moveTo(shotLayer);
        stage.draw();
        if (shape.attrs.shot != undefined) {
            shape.attrs.shot.updateRelativeLocation(shape.attrs.x, shape.attrs.y);
            target.cleanShotLog();
            target.updateTargetSore();
        }
    });

    this.canvas.on('dragmove', function(evt) {
        let shape = evt.target;
        if (shape.attrs.shot != undefined) {
            let location = shape.attrs.shot.getRelativeLocation(shape.attrs.x, shape.attrs.y);
            target.logShot(location);
        }
    });
}

Target.prototype = {
    TARGET_TO_CANVAS_SCALE: 0.85,

    draw: function () {
        for (let circle of this.circles) {
            if (circle.value >= this.minRing) {
                circle.draw();
            }
        }

        for (let shot of this.shots) {
            shot.draw();
        }

        this.textLayer.add(this.shotDenominationText);
        this.textLayer.add(this.targetTotalText);

        this.canvas.add(this.bgLayer);
        this.canvas.add(this.textLayer);
        this.canvas.add(this.shotLayer);
        this.canvas.add(this.dragLayer);

        this.updateTargetSore();
    },

    addCircle: function (circle) {
        circle.target = this;
        this.circles.push(circle);
        this.arrange();
    },

    addShot: function (shot) {
        shot.target = this;
        this.shots.push(shot);
    },

    arrange: function() {
        this.circles = this.circles.sort(function (a, b) {
            if (a.diameter > b.diameter) return -1;
            if (a.diameter < b.diameter) return 1;
            return 0;
        });
        this.updateScale();
    },

    updateScale: function () {
        let diameter = this.findMaxDiameter();
        if (diameter > 0) {
            this.ringScaleFactor = (2 * this.radius * this.TARGET_TO_CANVAS_SCALE) / diameter;
        }
    },

    findMaxDiameter: function() {
        for (let circle of this.circles) {
            if (circle.value >= this.minRing) {
                return circle.diameter;
            }
        }
    },

    getDenomination: function(shot, x, y) {
        let valueableRadius = Math.sqrt(x*x + y*y) - (shot.diameter / 2);
        let valueableCircle = null;

        for (let circle of this.circles) {
            if (circle.diameter / 2 > valueableRadius) {
                valueableCircle = circle;
            }
        }

        if (valueableCircle != null) {
            return valueableCircle.value;
        }

        return 0;
    },

    logShot: function(location) {
        this.shotDenominationText.setText(location.value);
        this.textLayer.draw();
    },

    cleanShotLog: function() {
        this.shotDenominationText.setText("");
        this.textLayer.draw();
    },

    getScore: function() {
        let score = 0;
        for (let shot of this.shots) {
            score += shot.value;
        }
        return score;
    },

    updateTargetSore: function() {
        this.targetTotalText.setText(this.getScore());
        this.textLayer.draw();
    },

    getResults: function() {
        let result = [];
        for (let shot of this.shots) {
            result.push(shot.getResult());
        }
        return result;
    }
};

//============================================================
function TargetCircle (diameter, color, value) {
    this.diameter = diameter;
    this.color = new TargetRingColor(color);
    this.value = value;
}

TargetCircle.prototype = {
    draw: function () {
        var circle = new Konva.Circle({
            x: this.target.centerX,
            y: this.target.centerY,
            radius: (this.diameter / 2 * this.target.ringScaleFactor),
            fill: this.color.fill,
            stroke: this.color.border,
            strokeWidth: 1
        });
        this.target.bgLayer.add(circle)
    },
};

//============================================================
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

//============================================================
function Shot (diameter, value, x, y) {
    this.diameter = diameter;
    this.fill = "#000066";
    this.border = "#66FFFF";
    this.value = value;
    this.x = x;
    this.y = y;
}

Shot.prototype = {
    draw: function () {
        let circle = new Konva.Circle({
            x: this.target.centerX + (this.x * this.target.ringScaleFactor),
            y: this.target.centerY + (this.y * this.target.ringScaleFactor),
            radius: (this.diameter / 2 * this.target.ringScaleFactor),
            fill: this.fill,
            stroke: this.border,
            strokeWidth: 1,
            draggable: this.target.editable,
            shot: this
        });

        if (this.target.editable) {
            circle.on('mouseover', function() {
                document.body.style.cursor = 'pointer';
            });

            circle.on('mouseout', function() {
                document.body.style.cursor = 'default';
            });
        }

        this.target.shotLayer.add(circle)
    },

    getRelativeLocation: function(x, y) {
        let result = {};
        result.x = (x - this.target.centerX) / this.target.ringScaleFactor;
        result.y = (y - this.target.centerY) / this.target.ringScaleFactor;
        result.value = this.target.getDenomination(this, result.x, result.y);

        return result;
    },

    updateRelativeLocation: function (x, y) {
        let result = this.getRelativeLocation(x, y);
        this.x = result.x;
        this.y = result.y;
        this.value = result.value;

        return result;
    },

    getResult: function() {
        let result = {};
        result.x = this.x;
        result.y = this.y;
        result.value = this.value;
        return result;
    }
};