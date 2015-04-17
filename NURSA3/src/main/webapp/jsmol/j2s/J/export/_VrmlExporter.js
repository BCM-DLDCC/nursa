Clazz.declarePackage ("J.export");
Clazz.load (["J.export.__CartesianExporter", "java.util.Hashtable", "JU.P3"], "J.export._VrmlExporter", ["java.lang.Boolean", "$.Float", "$.Short", "JU.List", "$.PT", "$.Quat", "J.export.UseTable", "JV.Viewer"], function () {
c$ = Clazz.decorateAsClass (function () {
this.useTable = null;
this.tempQ1 = null;
this.tempQ2 = null;
this.htSpheresRendered = null;
Clazz.instantialize (this, arguments);
}, J["export"], "_VrmlExporter", J["export"].__CartesianExporter);
Clazz.prepareFields (c$, function () {
this.tempQ1 =  new JU.P3 ();
this.tempQ2 =  new JU.P3 ();
this.htSpheresRendered =  new java.util.Hashtable ();
});
Clazz.makeConstructor (c$, 
function () {
Clazz.superConstructor (this, J["export"]._VrmlExporter, []);
this.useTable =  new J["export"].UseTable ("USE ");
this.commentChar = "# ";
});
$_M(c$, "output", 
function (pt) {
this.output (J["export"].___Exporter.round (this.scalePt (pt)));
}, "JU.T3");
$_V(c$, "outputHeader", 
function () {
this.output ("#VRML V2.0 utf8 Generated by Jmol " + JV.Viewer.getJmolVersion () + "\n");
this.output ("WorldInfo { \n");
this.output (" title " + JU.PT.esc (this.viewer.getModelSetName ()) + "\n");
this.output (" info [ \"Generated by Jmol " + JV.Viewer.getJmolVersion () + " \", \n");
this.output ("  \"http://www.jmol.org \", \n");
this.output ("  \"Creation date: " + this.getExportDate () + " \" ]\n");
this.output ("} \n");
this.output ("NavigationInfo { type \"EXAMINE\" } \n");
this.output ("Background { skyColor [" + this.rgbFractionalFromColix (this.backgroundColix) + "] } \n");
var angle = this.getViewpoint ();
this.output ("Viewpoint{fieldOfView " + angle);
this.output (" position ");
this.output (this.cameraPosition);
this.output (" orientation ");
this.output (this.tempP1);
this.output (" " + -this.viewpoint.angle);
this.output ("\n jump TRUE description \"v1\"\n}\n\n");
this.output (this.getJmolPerspective ());
this.output ("\nTransform{children Transform{translation ");
this.tempP1.setT (this.center);
this.tempP1.scale (-1);
this.output (this.tempP1);
this.output ("\nchildren [\n");
});
$_M(c$, "getViewpoint", 
function () {
this.viewer.getAxisAngle (this.viewpoint);
this.tempP1.set (this.viewpoint.x, this.viewpoint.y, (this.viewpoint.angle == 0 ? 1 : this.viewpoint.z));
return (this.aperatureAngle * 3.141592653589793 / 180);
});
$_V(c$, "outputFooter", 
function () {
this.useTable = null;
this.output ("\n]\n");
this.output ("}}\n");
});
$_M(c$, "outputAppearance", 
function (colix, isText) {
var def = this.useTable.getDef ((isText ? "T" : "") + colix);
this.output (" appearance ");
if (def.charAt (0) == '_') {
var color = this.rgbFractionalFromColix (colix);
this.output (" DEF " + def + " Appearance{material Material{diffuseColor ");
if (isText) this.output (" 0 0 0 specularColor 0 0 0 ambientIntensity 0.0 shininess 0.0 emissiveColor " + color + " }}");
 else this.output (color + " transparency " + J["export"].___Exporter.translucencyFractionalFromColix (colix) + "}}");
return;
}this.output (def);
}, "~N,~B");
$_V(c$, "outputCircle", 
function (pt1, pt2, radius, colix, doFill) {
if (doFill) {
this.output ("Transform{translation ");
this.tempV1.ave (pt1, pt2);
this.output (this.tempV1);
this.output (" children Billboard{axisOfRotation 0 0 0 children Transform{rotation 1 0 0 1.5708");
this.outputCylinderChildScaled (pt1, pt2, colix, 2, radius * 2000);
this.output ("}}}\n");
return;
}var child = this.useTable.getDef ("C" + colix + "_" + radius);
this.outputTransRot (pt1, pt2, 0, 0, 1);
this.tempP3.set (1, 1, 1);
this.tempP3.scale (radius);
this.output (" scale ");
this.output (this.tempP3);
this.output (" children ");
if (child.charAt (0) == '_') {
this.output ("DEF " + child);
this.output (" Billboard{axisOfRotation 0 0 0 children Transform{children");
this.output (" Shape{geometry Extrusion{beginCap FALSE convex FALSE endCap FALSE creaseAngle 1.57");
this.output (" crossSection [");
var rpd = 0.017453292;
var scale = 0.02 / radius;
for (var i = 0; i <= 360; i += 10) {
this.output (J["export"].___Exporter.round (Math.cos (i * rpd) * scale) + " ");
this.output (J["export"].___Exporter.round (Math.sin (i * rpd) * scale) + " ");
}
this.output ("] spine [");
for (var i = 0; i <= 360; i += 10) {
this.output (J["export"].___Exporter.round (Math.cos (i * rpd)) + " ");
this.output (J["export"].___Exporter.round (Math.sin (i * rpd)) + " 0 ");
}
this.output ("]}");
this.outputAppearance (colix, false);
this.output ("}}}");
} else {
this.output (child);
}this.output ("}\n");
}, "JU.P3,JU.P3,~N,~N,~B");
$_V(c$, "outputCone", 
function (ptBase, ptTip, radius, colix) {
radius = this.scale (radius);
var height = this.scale (ptBase.distance (ptTip));
this.outputTransRot (ptBase, ptTip, 0, 1, 0);
this.output (" children ");
var cone = "o" + Clazz.floatToInt (height * 100) + "_" + Clazz.floatToInt (radius * 100);
var child = this.useTable.getDef ("c" + cone + "_" + colix);
if (child.charAt (0) == '_') {
this.output ("DEF " + child + " Shape{geometry ");
cone = this.useTable.getDef (cone);
if (cone.charAt (0) == '_') {
this.output ("DEF " + cone + " Cone{height " + J["export"].___Exporter.round (height) + " bottomRadius " + J["export"].___Exporter.round (radius) + "}");
} else {
this.output (cone);
}this.outputAppearance (colix, false);
this.output ("}");
} else {
this.output (child);
}this.output ("}\n");
}, "JU.P3,JU.P3,~N,~N");
$_V(c$, "outputCylinder", 
function (ptCenter, pt1, pt2, colix, endcaps, radius, ptX, ptY, checkRadius) {
if (ptX == null) {
this.outputTransRot (pt1, pt2, 0, 1, 0);
} else {
this.output ("Transform{translation ");
this.output (ptCenter);
this.outputQuaternionFrame (ptCenter, ptY, pt1, ptX, 2, " ", "");
pt1.set (0, 0, -1);
pt2.set (0, 0, 1);
}this.outputCylinderChildScaled (pt1, pt2, colix, endcaps, radius);
this.output ("}\n");
if (endcaps == 3) {
this.outputSphere (pt1, radius * 1.01, colix, checkRadius);
this.outputSphere (pt2, radius * 1.01, colix, checkRadius);
}return true;
}, "JU.P3,JU.P3,JU.P3,~N,~N,~N,JU.P3,JU.P3,~B");
$_M(c$, "outputCylinderChildScaled", 
($fz = function (pt1, pt2, colix, endcaps, radius) {
this.output (" children ");
var length = this.scale (pt1.distance (pt2));
radius = this.scale (radius);
var child = this.useTable.getDef ("C" + colix + "_" + Clazz.floatToInt (length * 100) + "_" + radius + "_" + endcaps);
if (child.charAt (0) == '_') {
this.output ("DEF " + child);
this.output (" Shape{geometry ");
var cyl = this.useTable.getDef ("c" + J["export"].___Exporter.round (length) + "_" + endcaps + "_" + radius);
if (cyl.charAt (0) == '_') {
this.output ("DEF " + cyl + " Cylinder{height " + J["export"].___Exporter.round (length) + " radius " + radius + (endcaps == 2 ? "" : " top FALSE bottom FALSE") + "}");
} else {
this.output (cyl);
}this.outputAppearance (colix, false);
this.output ("}");
} else {
this.output (child);
}}, $fz.isPrivate = true, $fz), "JU.P3,JU.P3,~N,~N,~N");
$_V(c$, "outputEllipsoid", 
function (ptCenter, points, colix) {
this.output ("Transform{translation ");
this.output (ptCenter);
this.outputQuaternionFrame (ptCenter, points[1], points[3], points[5], 1, " ", "");
this.output (" children ");
this.tempP3.set (0, 0, 0);
this.outputSphereChildUnscaled (this.tempP3, 1.0, colix);
this.output ("}\n");
}, "JU.P3,~A,~N");
$_M(c$, "outputQuaternionFrame", 
function (ptCenter, ptX, ptY, ptZ, yScale, pre, post) {
this.tempQ1.setT (ptX);
this.tempQ2.setT (ptY);
var a = JU.Quat.getQuaternionFrame (ptCenter, this.tempQ1, this.tempQ2).toAxisAngle4f ();
if (!Float.isNaN (a.x)) {
this.output (" rotation");
this.output (pre);
this.output (a.x + " " + a.y + " " + a.z + " " + a.angle);
this.output (post);
}var sx = this.scale (ptX.distance (ptCenter));
var sy = this.scale (ptY.distance (ptCenter) * yScale);
var sz = this.scale (ptZ.distance (ptCenter));
this.output (" scale");
this.output (pre);
this.output (sx + " " + sy + " " + sz);
this.output (post);
}, "JU.P3,JU.P3,JU.P3,JU.P3,~N,~S,~S");
$_V(c$, "outputSurface", 
function (vertices, normals, colixes, indices, polygonColixes, nVertices, nPolygons, nFaces, bsPolygons, faceVertexMax, colix, colorList, htColixes, offset) {
this.output ("Shape {\n");
this.outputAppearance (colix, false);
this.output (" geometry IndexedFaceSet {\n");
if (polygonColixes != null) this.output (" colorPerVertex FALSE\n");
this.output ("coord Coordinate {\n   point [\n");
this.outputVertices (vertices, nVertices, offset);
this.output ("   ]\n");
this.output ("  }\n");
this.output ("  coordIndex [\n");
var map =  Clazz.newIntArray (nVertices, 0);
this.getCoordinateMap (vertices, map, null);
this.outputIndices (indices, map, nPolygons, bsPolygons, faceVertexMax);
this.output ("  ]\n");
if (normals != null) {
var vNormals =  new JU.List ();
map = this.getNormalMap (normals, nVertices, null, vNormals);
this.output ("  solid FALSE\n  normalPerVertex TRUE\n   normal Normal {\n  vector [\n");
this.outputNormals (vNormals);
this.output ("   ]\n");
this.output ("  }\n");
this.output ("  normalIndex [\n");
this.outputIndices (indices, map, nPolygons, bsPolygons, faceVertexMax);
this.output ("  ]\n");
}map = null;
if (colorList != null) {
this.output ("  color Color { color [\n");
this.outputColors (colorList);
this.output ("  ] } \n");
this.output ("  colorIndex [\n");
this.outputColorIndices (indices, nPolygons, bsPolygons, faceVertexMax, htColixes, colixes, polygonColixes);
this.output ("  ]\n");
}this.output (" }\n");
this.output ("}\n");
}, "~A,~A,~A,~A,~A,~N,~N,~N,JU.BS,~N,~N,JU.List,java.util.Map,JU.P3");
$_V(c$, "outputFace", 
function (face, map, faceVertexMax) {
this.output (map[face[0]] + " " + map[face[1]] + " " + map[face[2]] + " -1\n");
if (faceVertexMax == 4 && face.length == 4) this.output (map[face[0]] + " " + map[face[2]] + " " + map[face[3]] + " -1\n");
}, "~A,~A,~N");
$_M(c$, "outputNormals", 
function (vNormals) {
var n = vNormals.size ();
for (var i = 0; i < n; i++) {
this.output (vNormals.get (i));
}
}, "JU.List");
$_M(c$, "outputColors", 
function (colorList) {
var nColors = colorList.size ();
for (var i = 0; i < nColors; i++) {
var color = this.rgbFractionalFromColix (colorList.get (i).shortValue ());
this.output (" ");
this.output (color);
this.output ("\n");
}
}, "JU.List");
$_M(c$, "outputColorIndices", 
function (indices, nPolygons, bsPolygons, faceVertexMax, htColixes, colixes, polygonColixes) {
var isAll = (bsPolygons == null);
var i0 = (isAll ? nPolygons - 1 : bsPolygons.nextSetBit (0));
for (var i = i0; i >= 0; i = (isAll ? i - 1 : bsPolygons.nextSetBit (i + 1))) {
if (polygonColixes == null) {
this.output (htColixes.get (Short.$valueOf (colixes[indices[i][0]])) + " " + htColixes.get (Short.$valueOf (colixes[indices[i][1]])) + " " + htColixes.get (Short.$valueOf (colixes[indices[i][2]])) + " -1\n");
if (faceVertexMax == 4 && indices[i].length == 4) this.output (htColixes.get (Short.$valueOf (colixes[indices[i][0]])) + " " + htColixes.get (Short.$valueOf (colixes[indices[i][2]])) + " " + htColixes.get (Short.$valueOf (colixes[indices[i][3]])) + " -1\n");
} else {
this.output (htColixes.get (Short.$valueOf (polygonColixes[i])) + "\n");
}}
}, "~A,~N,JU.BS,~N,java.util.Map,~A,~A");
$_V(c$, "outputSphere", 
function (ptCenter, radius, colix, checkRadius) {
radius = this.scale (radius);
var check = J["export"].___Exporter.round (this.scalePt (ptCenter)) + (checkRadius ? " " + Clazz.floatToInt (radius * 100) : "");
if (this.htSpheresRendered.get (check) != null) return;
this.htSpheresRendered.put (check, Boolean.TRUE);
this.outputSphereChildUnscaled (ptCenter, radius, colix);
}, "JU.P3,~N,~N,~B");
$_M(c$, "outputSphereChildUnscaled", 
function (ptCenter, radius, colix) {
var iRad = Clazz.floatToInt (radius * 100);
var child = this.useTable.getDef ("S" + colix + "_" + iRad);
this.output ("Transform{translation ");
this.output (ptCenter);
this.output (" children ");
if (child.charAt (0) == '_') {
this.output ("DEF " + child);
this.output (" Shape{geometry Sphere{radius " + radius + "}");
this.outputAppearance (colix, false);
this.output ("}");
} else {
this.output (child);
}this.output ("}\n");
}, "JU.P3,~N,~N");
$_V(c$, "outputTextPixel", 
function (pt, argb) {
var color = this.rgbFractionalFromArgb (argb);
this.output ("Transform{translation ");
this.output (pt);
this.output (" children ");
var child = this.useTable.getDef ("p" + argb);
if (child.charAt (0) == '_') {
this.output ("DEF " + child + " Shape{geometry Sphere{radius 0.01}");
this.output (" appearance Appearance{material Material{diffuseColor 0 0 0 specularColor 0 0 0 ambientIntensity 0.0 shininess 0.0 emissiveColor " + color + " }}}");
} else {
this.output (child);
}this.output ("}\n");
}, "JU.P3,~N");
$_M(c$, "outputTransRot", 
($fz = function (pt1, pt2, x, y, z) {
this.output ("Transform{");
this.outputTransRot (pt1, pt2, x, y, z, " ", "");
}, $fz.isPrivate = true, $fz), "JU.P3,JU.P3,~N,~N,~N");
$_M(c$, "outputTransRot", 
function (pt1, pt2, x, y, z, pre, post) {
this.tempV1.ave (pt2, pt1);
this.output ("translation");
this.output (pre);
this.output (this.tempV1);
this.output (post);
this.tempV1.sub (pt1);
this.tempV1.normalize ();
this.tempV2.set (x, y, z);
this.tempV2.add (this.tempV1);
this.output (" rotation");
this.output (pre);
this.output (this.tempV2);
this.output (" ");
this.output (J["export"].___Exporter.round (3.141592653589793));
this.output (post);
}, "JU.P3,JU.P3,~N,~N,~N,~S,~S");
$_V(c$, "outputTriangle", 
function (pt1, pt2, pt3, colix) {
this.output ("Shape{geometry IndexedFaceSet{solid FALSE coord Coordinate{point[");
this.output (pt1);
this.output (" ");
this.output (pt2);
this.output (" ");
this.output (pt3);
this.output ("]}coordIndex[ 0 1 2 -1 ]}");
this.outputAppearance (colix, false);
this.output ("}\n");
}, "JU.P3,JU.P3,JU.P3,~N");
$_V(c$, "plotText", 
function (x, y, z, colix, text, font3d) {
if (z < 3) z = this.viewer.getFrontPlane ();
var useFontStyle = font3d.fontStyle.toUpperCase ();
var preFontFace = font3d.fontFace.toUpperCase ();
var useFontFace = (preFontFace.equals ("MONOSPACED") ? "TYPEWRITER" : preFontFace.equals ("SERIF") ? "SERIF" : "SANS");
this.output ("Transform{translation ");
this.tempP3.set (x, y, z);
this.viewer.unTransformPoint (this.tempP3, this.tempP1);
this.output (this.tempP1);
this.output (" children ");
var child = this.useTable.getDef ("T" + colix + useFontFace + useFontStyle + "_" + text);
if (child.charAt (0) == '_') {
this.output ("DEF " + child + " Billboard{axisOfRotation 0 0 0 children Transform{children Shape{");
this.outputAppearance (colix, true);
this.output (" geometry Text{fontStyle ");
var fontstyle = this.useTable.getDef ("F" + useFontFace + useFontStyle);
if (fontstyle.charAt (0) == '_') {
this.output ("DEF " + fontstyle + " FontStyle{size 0.4 family \"" + useFontFace + "\" style \"" + useFontStyle + "\"}");
} else {
this.output (fontstyle);
}this.output (" string " + JU.PT.esc (text) + "}}}}");
} else {
this.output (child);
}this.output ("}\n");
}, "~N,~N,~N,~N,~S,javajs.awt.Font");
});
