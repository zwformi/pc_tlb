$(function () {
    //初始化绑定默认的属性
    $.upLoadDefaults = $.upLoadDefaults || {};
    $.upLoadDefaults.property = {
        multiple: true, //是否多文件
        water: false, //是否加水印
        thumbnail: true, //是否生成缩略图
        sendurl: null, //发送地址
        filetypes: "jpg,png,gif,jpeg", //文件类型
        filesize: "4096", //文件大小
        btntext: "浏览...", //上传按钮的文字
        swf: null //SWF上传控件相对地址
    };
    //初始化上传控件
    $.fn.InitUploader = function (b) {
        var fun = function (parentObj) {
            var p = $.extend({}, $.upLoadDefaults.property, b || {});
            var btnObj = $('<div class="upload-btn">' + p.btntext + '</div>').appendTo(parentObj);
            //初始化属性
            p.sendurl += "?action=UpLoadFile";

            //初始化WebUploader
            var uploader = WebUploader.create({
                auto: false, //自动上传
                swf: p.swf, //SWF路径
                server: p.sendurl, //上传地址
                pick: {
                    id: btnObj,
                    multiple: p.multiple
                },
                accept: {
                    extensions: p.filetypes
                },
                compress: false,
                fileVal: 'filedata', //上传域的名称
                fileSingleSizeLimit: p.filesize * 1024 //文件大小
            });

            //当validate不通过时，会以派送错误事件的形式通知
            uploader.on('error', function (type) {
                switch (type) {
                    case 'Q_EXCEED_NUM_LIMIT':
                        alert("错误：上传文件数量过多！");
                        break;
                    case 'Q_EXCEED_SIZE_LIMIT':
                        alert("错误：文件总大小超出限制！");
                        break;
                    case 'F_EXCEED_SIZE':
                        alert("错误：文件大小超出限制！");
                        break;
                    case 'Q_TYPE_DENIED':
                        alert("错误：禁止上传该类型文件！");
                        break;
                    case 'F_DUPLICATE':
                        alert("错误：请勿重复上传该文件！");
                        break;
                    default:
                        alert('错误代码：' + type);
                        break;
                }
            });

            //当有文件添加进来的时候
            uploader.on('fileQueued', function (file) {
                //如果是单文件上传，把旧的文件地址传过去
            	 
                if (!p.multiple) {
                    uploader.options.formData.DelFilePath = parentObj.siblings(".upload-path").val();
                }
               $list = $("#imgboxs");
                var thumbnailWidth = 100;   //缩略图高度和宽度 （单位是像素），当宽高度是0~1的时候，是按照百分比计算，具体可以看api文档  
                var thumbnailHeight = 100;  
               
                
             	var $td = $("<div class=\"imgbox\" imgid=\""+file.id+"\">"+
             			"<a href=\"\" rel=\"example_group\" target=\"_blank\">"+
               
                "     <img width=\"100\" height=\"100\"/>"+
          
                "   </a>"+
                "</div>"),$img = $td.find('img');
             	
             	 var progressCancel = $("<div  del_id=\""+file.id+"\" style=\"display:none\" class=\"delete\">删除<div>").appendTo($td);
         		 progressCancel.click(function () {
                     //绑定点击事件
               uploader.cancelFile(file);
               $(this).parent().remove();
              
           });
       	 
    
               //绑定点击事件
              
                $list.append( $td );  
              
              //图片删除层-资质
            	$("#imgboxs").on("mouseenter",".imgbox",function(){
            			$(this).find(".delete").show();
                        
                     });
//                 	 
//                });
                $("#imgboxs").on("mouseleave",".imgbox",function(){
                	$(this).find(".delete").hide();
                });
            	
                // 创建缩略图  
                // 如果为非图片文件，可以不用调用此方法。  
                // thumbnailWidth x thumbnailHeight 为 100 x 100  
                uploader.makeThumb( file, function( error, src ) {   //webuploader方法  
                    if ( error ) {  
                        $img.replaceWith('<span>不能预览</span>');  
                        return;  
                    }  
           
                    $img.attr( 'src', src );  
                    $td.find('a').attr('href',src);
                }, thumbnailWidth, thumbnailHeight );  
            });  
           
            
            //文件上传过程中创建进度条实时显示
            uploader.on('uploadProgress', function (file, percentage) {
                var progressObj = parentObj.children(".upload-progress");
                progressObj.children(".txt").html(file.name);
                progressObj.find(".bar b").width(percentage * 100 + "%");
            });

            //当文件上传出错时触发
            uploader.on('uploadError', function (file, reason) {
                uploader.removeFile(file); //从队列中移除
                alert(file.name + "上传失败，错误代码：" + reason);
            });

            //当文件上传成功时触发
            uploader.on('uploadSuccess', function (file, data) {
                if (data.status == '0') {
                    var progressObj = parentObj.children(".upload-progress");
                    progressObj.children(".txt").html(data.msg);
                }
                uploader.removeFile(file); //从队列中移除
            });

            //不管成功或者失败，文件上传完成时触发
            uploader.on('uploadComplete', function (file) {
                var progressObj = parentObj.children(".upload-progress");
                progressObj.children(".txt").html("上传完成");
                //如果队列为空，则移除进度条
                if (uploader.getStats().queueNum == 0) {
                    progressObj.remove();
                }
            });
            $btn = $("#upload");
            $btn.on( 'click', function() {  
                console.log("上传..."); 
                uploader.option('formData',{"owning_company":$("#owning_company_id").val()})
                uploader.upload();  
                console.log("上传成功");  
              });  
        };
        return $(this).each(function () {
            fun($(this));
        });
    }
    
    function createimgdiv(){
		$("a[rel=example_group]").fancybox({
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'titlePosition' 	: 'over',
			'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
				return '<span id="fancybox-title-over">Image：' + (currentIndex + 1) + ' / ' + currentArray.length + '</span><strong>'+(title.length ? ' &nbsp; ' + title : '') + '</strong>';
			}
		});
	}
});