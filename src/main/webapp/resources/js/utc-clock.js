/**
 * @author Ruslan Lazin
 */

    function GetClock(){
        var d=new Date();
        var nhour=d.getHours(),nmin=d.getMinutes(),nsec=d.getSeconds();
        if(nmin<=9) nmin="0"+nmin;
        if(nsec<=9) nsec="0"+nsec;

        document.getElementById('clockbox').innerHTML=""+nhour+":"+nmin+":"+nsec+"";
    }

window.onload=function(){
        GetClock();

        setInterval(GetClock,1000);
    };
