$(document).ready(function() {
//$("#pageLoader").hide();
/* Pagination */
    var $_GET = {};
    
    document.location.search.replace(/\??(?:([^=]+)=([^&]*)&?)/g, function () {
        function decode(s) {
            return decodeURIComponent(s.split("+").join(" "));
        }
    
        $_GET[decode(arguments[1])] = decode(arguments[2]);
    });
    
    var currentPage = "";
    if($_GET["currentPage"]){
        currentPage = $_GET["currentPage"];
    }else{
        currentPage = 0;
    }
//    var url = HOST_URL + "/dashboard/users?currentPage=";
    $.ajax({
        url:"http://localhost:8080/rest/list?currentPage=",
        type: "GET",
        success:function(data) {			    
            var pageSize = data.totalPages;
            pagination(currentPage, pageSize, url);
        }
    });
    
    function pagination(currentPage, pageSize, url){
        var forlimit = 3;
        
        if(pageSize < 2){
            null;
        }else{
            if(currentPage > 4){
                var prev  = parseInt(currentPage) - 1;
                var yaz1 = '<a href="'+url+'0" class="btn btn-icon btn-sm btn-light-primary mr-2 my-1"><i class="ki ki-bold-double-arrow-back icon-xs"></i></a>';
                var yaz2 = '<a href="'+url+(parseInt(currentPage)-1)+'" class="btn btn-icon btn-sm btn-light-primary mr-2 my-1"><i class="ki ki-bold-arrow-back icon-xs"></i></a>';
                $("#sayfalama").append(yaz1+yaz2);
            }

            for(var i = (parseInt(currentPage) - parseInt(forlimit)); parseInt(i) < parseInt(currentPage) + (parseInt(forlimit) + 1); i++){
                if(parseInt(i) > 0 && parseInt(i) <= parseInt(pageSize)){
                    if(i == parseInt(currentPage)+1){
                        var yaz3 = '<a href="#" class="btn btn-icon btn-sm border-0 btn-hover-primary active mr-2 my-1">'+i+'</a>';
                        $("#sayfalama").append(yaz3);
                    }else{
                        var yaz4 = '<a href="'+url+(i-1)+'" class="btn btn-icon btn-sm border-0 btn-hover-primary mr-2 my-1">'+i+'</a>';
                        $("#sayfalama").append(yaz4);
                    }
                }
            }

            if(currentPage <= pageSize - 4){
                var next  = parseInt(currentPage) + 1;
                var yaz5 = '<a href="'+url+next+'" class="btn btn-icon btn-sm btn-light-primary mr-2 my-1"><i class="ki ki-bold-arrow-next icon-xs"></i></a>';
                var yaz6 ='<a href="'+url+(parseInt(pageSize)-1)+'" class="btn btn-icon btn-sm btn-light mr-2 my-1"><i class="ki ki-bold-double-arrow-next icon-xs"></i></a>'
                $("#sayfalama").append(yaz5+yaz6);
            }
            
            
        }
    }
});
    