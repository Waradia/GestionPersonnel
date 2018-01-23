/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 var li=angular.module('MonApp', []);
         li.controller('monController', function ($scope){
             //$scope.message="";
             var operateur=" ";
             $scope.val=[{}];
             $scope.add=function()
             {
                 $scope.res=$scope.op1+$scope.op2;
                 operateur="+";
             }
             $scope.mult=function()
             {
                 $scope.res=$scope.op1*$scope.op2;
                 operateur="*";
             }
             $scope.diff=function()
             {
                 $scope.res=$scope.op1-$scope.op2;
                 operateur="-";
             }
             $scope.div=function()
             {
                 $scope.res=$scope.op1/$scope.op2;
                 operateur="/";
             }
             $scope.memoriser=function()
             {
                 $scope.memoire=$scope.res;
                 var element={
                     op1: $scope.op1,
                     op2 : $scope.op2,
                     result : $scope.memoire,
                     oper : oprateur 
                 };
                 $scope.val.push(element);
                 
             }
             $scope.recharger=function()
             {
                 $scope.op2=$scope.memoire;
                 $scope.op1="";
                 $scope.res="";
             }
             
 });
