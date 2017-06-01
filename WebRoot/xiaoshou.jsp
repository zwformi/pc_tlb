<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link href="<%=basePath %>css/xiaoshou.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/iconfont.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<%=basePath %>css/cs.css"/>
	<link type="text/css" rel="stylesheet" href="<%=basePath %>css/buy.css" />
    <script src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
    <style>
    	.top .item img{
    		width:100%;
    	}
    	.item a:link{
    		font-size:18px\9;
    		color:#373737\9;
    	}
    	.item a:link:hover{
    		color:#ffffff;
    	}
    </style>
</head>
<body style="background-color:#F5F5F5;">
<jsp:include page="top.jsp"></jsp:include>
    <div class="banner"></div>
    <div class="top" style="width:1200px;">
        <div class="item">
            <a href="#email">
            	<img src="img/emailitem.png">
            	邮件系统实施服务
            </a>
            <a href="#virtualization">
            	<img src="img/virtualizationitem.png">
            	虚拟化实施服务
            </a>
            <a href="#meeting">
            	<img src="img/meetingitem.png">
            	小型视频会议系统
            </a>
            <a href="#crm">
           		<img src="img/crmitem.png">
            	客户关系管理系统
            </a>
            <a href="#assistant">
            	<img src="img/assistantitem.png">
            	移动销售助手
            </a>
            <a href="#education">
            	<img src="img/educationitem.png">
            	互联网教育
            </a>
        </div>
    </div>
    <div class="email" id="email">
        <div class="title">
            <img src="img/title2.png" class="left">
            <p class="left">邮件系统实施服务</p>
        </div>
        <div class="main">
            <img src="img/email.jpg" style="width:450px;" class="left">
            <div class="left part_one">
                <h3>服务目标</h3>
                <p>通过部署微软Exchange邮件系统，帮助客户实现：</p>
                <ul>
                    <li>灵活可靠的消息系统：根据企业独特需求灵活地定制部署，提供简单的方式，实现具有高可用性、安全性、功能强大的电子邮件服务。</li>
                    <li>就地访问：通过对Web访问方式以及类型丰富的移动终端的支持来满足信息工作者随时随地访问个人信息的需求。</li>
                    <li>保护和遵守法规：通过集中管理的信息控制功能，可以轻松改进企业通信和电子邮件的保护。</li>
                    <li>促成提供简约管理、帮助保护通信、控制部署、管理和合规性成本，从而帮助企业提升业绩。</li>
                </ul>
            </div>
            <div class="part_two">
                <h3>服务价值</h3>
                <p>部署微软Exchange电子邮件系统后，客户享受以下裨益：</p>
                <ul>
                    <li>通过交流方式的集成，节省员工用于沟通的时间：对最终用户来说，可以快速发送、接收并发现自己所需要的准确的信息资源。而不必关心这些信息是采用何种方式接收到的，也不用管这些信息在企业内的具体存放位置。</li>
                    <li>通过统一的收件箱，汇集用户日常工作需要的各种信息：统一消息帮助用户整合信息资源。它能够无缝地将用户的电子邮件、语音邮件、日程安排数据和传真信息传递到各自的收件箱中。用户可以方便地排序、管理和针对具体信息进行操作而无需在不同的应用和系统之间进行切换。</li>
                    <li>通过强大的消息访问工具，提供随时随地访问功能，提高员工的工作效率：用户可以使用Microsoft Office Outlook®, Outlook Web Access、型号众多的移动设备甚至是普通的电话等自己早已熟悉的客户端产品随时随地以自己希望的方式访问统一消息提供的丰富的功能。这就使得系统可以针对用户、工作量和工作性质以及工作流程的不同提供最为合适的个性化的访问方式。这种个性化有助于提供信息工作者的生产效率，同时给予他们灵活选择工作方式的自由。</li>
                    <li>整合各种系统的管理和维护，节约企业交流成本：整合的统一消息系统使得对站点（Site）和服务器的集中变得可行，这就可以减少为了提供语音邮件以及传真等服务所需要的总的服务器数量。特别是对于具有远程办公地点或者分支机构的组织来说，集中和整合可以极大地降低运营维护和保有成本。同时，可以有效利用企业在电子邮件系统上的投资。</li>
                    <li>通过统一的管理和控制，提高企业IT管理效率：单一的消息基础架构、消息存储、用户目录和传输拓扑的采用使得IT管理人员能够从一些基本却繁重的系统维护工作中解放出来，提高了IT管理效率，同时节省了成本。</li>
                    <li>加强安全管理，设定规则和权限：增强的邮件路由、审批、限额管理以及内置的邮件信息归档功能，便于企业对消息数据的审计和进一步处理。</li>
                </ul>
            </div>
            <div>
                <h3>方案架构</h3>
                <p>采用微软最新的Windows Server 2012 R2+Exchange Server 2013平台。部署两台域控制器为邮件系统提供邮件系统配置信息存储、邮箱信息、地址列表服务以及其他跟收件人相关的信息。在内部网络部署至少2台邮箱服务器，利用Exchange Server 2013高可用性组技术实现用户的邮件有多个副本，保证邮箱数据的高可用性，部署至少2台客户端访问服务器，使用 NLB 或基于第三方硬件的网络负载平衡设备，以实现客户端访问及内部邮件传输的高可用性。在DMZ区域部署2台边缘传输服务器，使用NLB或多条MX记录实现外部邮件传输的冗余。</p>
            </div>
            <div class="part_four">
                <h3>服务报价</h3>
                <div class="left w_server">
                    <p>Windows Server 2012 R2 标准版</p>
                    <p class="price">¥21,850.00</p>
                </div>
                <span class="left"></span>
                <div class="left w_cal">
                    <p>Windows Server CAL</p>
                    <p class="price">¥36,200.00</p>
                </div>
                <span class="left"></span>
                <div class="left ex_server">
                    <p>Exchange Server 2013 标准版</p>
                    <p class="price">¥17,524.00</p>
                </div>
                <span class="left"></span>
                <div class="left w_cal">
                    <p>Exchange Server 标准版CAL</p>
                    <p class="price">¥83,600.00</p>
                </div>
                <span class="left"></span>
                <div class="left bushu">
                    <p>部署实施</p>
                    <p class="price">¥30,000.00</p>
                </div>
                <span class="left equal"></span>
                <div class="left all">
                    <p class="price">¥189,174.00</p>
                    <a href="/buy/buy_show.json?ID=80">去结算</a>
                </div>
            </div>
            <div class="remarks">
                <p>备注：具体根据环境、功能需求而定</p>
            </div>
        </div>
    </div>
    <div class="virtualization" id="virtualization">
        <div class="title">
            <img src="img/title2.png" class="left">
            <p class="left">服务器虚拟化设施服务</p>
        </div>
        <div class="main">
            <img src="img/service.png" style="width:450px;margin:80px 0px;" class="right">
            <div class="left part_one">
                <h3>服务目标</h3>
                <p>帮助客户部署基于微软 Hyper-V虚拟化技术的服务器虚拟化数据中心平台，提高硬件资源利用率，提升关键应用的业务连续性，加强IT基础设施的运维管理，以提升企业信息化发展水平。</p>
                <h3>服务价值</h3>
                <p>部署微软Exchange电子邮件系统后，客户享受以下裨益：</p>
                <ul>
                    <li>
                        大大降低TCO
                        <ul>
                            <li>通过服务器整合，控制和减少物理服务器的数量，明显提高每个物理服务器及其CPU的资源利用率，从而降低硬件成本。</li>
                            <li>降低运营和维护成本， 包括数据中心空间、机柜、网线，耗电量，冷气空调和人力成本等。</li>
                        </ul>
                    </li>
                    <li>
                        提高运营效率和服务水平
                        <ul>
                            <li>主动地提前规划资源增长，加快新服务器和应用的部署，大大降低服务器重建和应用加载时间。</li>
                            <li>进行快速的硬件维护和升级。</li>
                            <li>避免了单机应用单点故障，实现应用的7×24小时不间断运转。</li>
                            <li>帮助企业建立业务和IT资源之间的关系，使IT服务和业务优先级对应。</li>
                        </ul>
                    </li>
                    <li>
                        旧硬件和操作系统的投资保护
                        <ul>
                            <li>不再担心旧系统的兼容性，维护和升级等一系列问题。</li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div>
                <h3>方案架构</h3>
                <p>本方案使用Windows Server 2012 R2作为服务器虚拟化的技术架构平台，根据应用系统架构或高可用性要求，部署高性能需求虚拟机服务器群集、低性能需求虚拟机服务器群组、虚拟机复制群组等三种虚拟化架构模式，部署System Center 2012 R2管理套件实现对虚拟化平台的统一集中管理、物理机、虚拟机及应用系统的监控及环境配置、物理机及虚拟机的备份保护，以及提供给最终用户访问的自助服务门户，实现用户自助式的虚拟机创建及管理服务。</p>
            </div>
            <div class="part_four">
                <h3>服务报价</h3>
                <div class="left system">
                    <p>System Center 2012 R2</p>
                    <p class="price">¥20,000.00</p>
                </div>
                <span class="left"></span>
                <div class="left w_cal">
                    <p>System Center 2012 R2 CAL</p>
                    <p class="price">¥13,290.00</p>
                </div>
                <span class="left"></span>
                <div class="left bushu">
                    <p>部署实施</p>
                    <p class="price">¥45,000.00</p>
                </div>
                <span class="left equal"></span>
                <div class="left all">
                    <p class="price">¥78,290.00</p>
                    <a href="/buy/buy_show.json?ID=160">去结算</a>
                </div>
            </div>
            <div class="remarks">
                <p>备注：具体根据环境、功能需求而定</p>
            </div>
        </div>
    </div>
    <div class="meeting" id="meeting">
        <div class="title">
            <img src="img/title2.png" class="left">
            <p class="left">硬件类小型视频会议系统</p>
        </div>
        <div class="main">
            <img src="img/meeting.jpg" style="width:450px;margin-top:90px;" class="left">
            <div class="left part_one">
                <h3>系统设计原则</h3>
                <ul>
                    <li>
                        先进性原则
                        <ul>
                            <li>系统必须严格遵循国际标准、国家标准和国内通信行业的规范要求； </li>
                            <li>需符合视频技术以及通信行业的发展趋势，并确保采用当前成熟的产品技术；</li>
                            <li>所有的系统采用最先进的技术，确保今后相当长的时间内技术上不会落伍。</li>
                        </ul>
                    </li>
                    <li>
                        开放式原则
                        <ul>
                            <li>必须完全符合H323标准框架协议；必须采用业界标准的视音频编解码协议；  </li>
                            <li>必须采用开放式标准设计，兼容标准的视讯系统和设备，确保可与其他厂家标准的产品有效互通；</li>
                            <li>满足今后的发展，留有充分的扩充余地；</li>
                            <li>建议选择国内主流通信厂家的设备，确保产品得到持续的技术支持和可靠的服务。尽可能避免选择小厂家的以及采用非标准协议的设备。</li>
                        </ul>
                    </li>
                    <li>
                        可靠性原则
                        <ul>
                            <li>确保系统具有高度的安全性，不易感染软件病毒；  </li>
                            <li>对工作环境要求较低，环境适应能力要强； </li>
                            <li>系统设备安装使用简单，无需专业人员维护；</li>
                            <li>系统需要满足7×24小时无人职守方式稳定的工作。</li>
                        </ul>
                    </li>
                    <li>
                        全业务兼容原则
                        <ul>
                            <li>要求系统不仅能够提供视讯会议功能，还需要支持丰富的附加业务，满足今后不同业务的建设和使用需求。 </li>
                            <li>鉴于视频会议的技术发展趋势和用户的实际需求，本建议书技术方案遵循先进性、开放性、可靠性、全业务兼容性的设计原则，结合用户的投资，推荐采用中国最大通信设备制造商华为公司的视讯产品。 </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="part_two">
                <h3>产品特点 </h3>
                <ul>
                    <li>
                        一体化精巧设计，易部署、易安装
                        <ul>
                            <li>TE30采用一体化紧凑型设计，内置摄像头和麦克风，体积小，接口简洁，连线方便，安装简单。 </li>
                            <li>支持Wi-Fi无线接入，只需插上电源，连上电视机， 5分钟即可安装入会。</li>
                            <li>提供配套支架，支持侧面墙、电视上方、倒装等多种上架方式。</li>
                        </ul>
                    </li>
                    <li>
                        语音呼叫
                        <ul>
                            <li>TE30支持中英文语音呼叫功能, 通过说出会场名称，可直接入会，简单方便。 </li>
                        </ul>
                    </li>
                    <li>
                        无线Wi-Fi接入
                        <ul>
                            <li>TE30内置Wi-Fi模块，支持无线网络接入、无线麦克风、无线辅流，部署简洁，使用方便。</li>
                        </ul>
                    </li>
                    <li>
                        人性化的界面和遥控器设计，简单易用
                        <ul>
                            <li>遥控器：集成最常用按键，小巧简洁，掌心掌控，方便实用。</li>
                            <li>无线触控屏（可选）：智能操作，所见即所得。</li>
                            <li>配置简单: 初次安装，自动配置向导；LCD屏实时显示本地会场信息（IP地址、会议号码）。</li>
                            <li>操作简单：人性化设计和操作思路，一目了然，易于掌握；一键入会，一键呼叫，简洁易用。</li>
                        </ul>
                    </li>
                    <li>
                        新一代VME+HP双核技术，成就低带宽极致高清体验
                        <ul>
                            <li>支持新一代VME和H.264 HP，节省50%带宽需求。</li>
                            <li>新一代VME、智能人脸检测、前后端视频图像处理，改善灯光适应性、降低带宽消耗、提升图像清晰度、增强运动图像效果。</li>
                            <li>内置麦克风，6m拾音距离、双声道宽频语音AAC-LD、独有的回声抵消和噪声抑制技术，提供高保真语音效果。</li>
                        </ul>
                    </li>
                    <li>
                        良好网络适应性和安全性，打造安全稳定视频会议系统
                        <ul>
                            <li>支持H.264 SVC技术，能够适应不同线路带宽、不同的设备能力、不同网络环境的组网需求。</li>
                            <li>独有SEC技术，在网络丢包率高达20％情况下，确保良好的视频图像效果。</li>
                            <li>专有IRC技术，自动侦测网络业务占用带宽变化情况，智能选择适合各种网络带宽的最佳图像分辨率，保持会议良好质量。</li>
                            <li>支持标准H.460和独有SNP防火墙穿越技术，可使视频通话方通过防火墙实现公私网、不同私网的安全连接。</li>
                            <li>H.235媒体流和信令加密、SRTP、TLS、HTTPS等多重安全加密措施，充分适应网络资源，保障会议系统安全稳定运行。</li>
                        </ul>
                    </li>
                    <li>
                        融合互通，丰富、实用的业务功能
                        <ul>
                            <li>互通性： 与业界主流标准终端和网络侧设备良好互通。</li>
                            <li>UC融合：支持与微软的Lync2010™ / Lync2013TM和OCS2007R2互通。</li>
                            <li>IMS融合：支持与IMS网络无缝融合。</li>
                            <li>主叫呼集：业界首创，通过终端用户预约和召开立即多点视频会议像打电话一样方便易用。</li>
                            <li>丰富的API接口：提供丰富的第三方集成开发API接口，方便系统集成和定制。</li>
                        </ul>
                    </li>
                    <li>
                        内置高清MCU功能(可选)
                        <ul>
                            <li>接入容量（非加密会议）：最大4个高清视频终端+3个语音终端。</li>
                            <li>接入容量（加密会议）：最大4个高清视频终端。</li>
                            <li>最大接入带宽：6M。</li>
                            <li>智能接入：支持高清、标清视频和VOIP终端混合接入；支持H.323和SIP终端混合接入；不同音视频协议、不同带宽的终端智能接入，会议呈现最佳的音视频效果。</li>
                            <li>丰富的功能：支持每端口自动多画面、声控切换、申请主席、广播会场、H.239双流、BFCP双流等，充分满足中小企业投资小、维护成本低等需求。</li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="part_four">
                <h3>服务报价</h3>
                <div class="left TE30_C">
                    <p>华为 TE30-C含多点功能</p>
                </div>
                <span class="left"></span>
                <div class="left bushu">
                    <p>部署实施</p>
                </div>
                <span class="left equal"></span>
                <div class="left all">
                    <p class="price">¥36,599.00</p>
                    <a href="/buy/buy_show.json?ID=272">去结算</a>
                </div>
            </div>
            <div class="remarks">
                <p>备注：实际产品配置和实施服务依据客户具体环境所需而定</p>
            </div>
        </div>
    </div>
    <div class="crm" id="crm">
        <div class="title">
            <img src="img/title2.png" class="left">
            <p class="left">客户关系管理系统</p>
        </div>
        <div class="main">
            <img src="img/CRM.jpg" style="width:450px;" class="right">
            <div class="left part_one">
                <img src="img/crm1.jpg">
            </div>
            <div>
                <img src="img/crm_main.jpg">
            </div>
            <div class="part_four">
                <h3>服务报价</h3>
                <div class="platform">
                    <p>平台产品报价</p>
                    <div class="left w_cal">
                        <p>DynCRMProCAL</p>
                        <p class="price">¥115,000.00</p>
                    </div>
                    <span class="left"></span>
                    <div class="left crmsvr">
                        <p>DynCRMSvr</p>
                        <p class="price">¥65,000.00</p>
                    </div>
                    <span class="left equal"></span>
                    <div class="left all">
                        <p class="price" style="padding-top:18px;">¥180,000.00</p>
                    </div>
                </div>
                <div class="platform">
                    <p>产品报价</p>
                    <div class="left survey">
                        <p>需求调研</p>
                        <p class="price">¥44,000.00</p>
                    </div>
                    <span class="left"></span>
                    <div class="left design">
                        <p>方案设计</p>
                        <p class="price">¥22,000.00</p>
                    </div>
                    <span class="left"></span>
                    <div class="left software">
                        <p>软件开发</p>
                        <p class="price">¥118,800.00</p>
                    </div>
                    <span class="left"></span>
                    <div class="left test">
                        <p>系统测试</p>
                        <p class="price">¥39,600.00</p>
                    </div>
                    <span class="left"></span>
                    <div class="left train">
                        <p>上线培训</p>
                        <p class="price">¥16,500.00</p>
                    </div>
                    <span class="left equal"></span>
                    <div class="left all">
                        <p class="price" style="padding-top:18px;">¥240,900.00</p>
                    </div>
                </div>
                <div class="platform">
                    <p>总报价</p>
                    <div class="left pingtai">
                        <p>平台产品报价</p>
                        <p class="price">¥180,000.00</p>
                    </div>
                    <span class="left"></span>
                    <div class="left product">
                        <p>产品报价</p>
                        <p class="price">¥240,900.00</p>
                    </div>
                    <span class="left equal"></span>
                    <div class="left all">
                        <p class="price">￥450000.00</p>
                        <a href="/buy/buy_show.json?ID=161">去结算</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="assistant" id="assistant">
        <div class="title">
            <img src="img/title2.png" class="left">
            <p class="left">移动销售助手</p>
        </div>
        <div class="main">
            <img src="img/assistant.png" style="margin-top:40px;" class="left">
            <div class="left part_one">
                <ul>
                    <li>
                        <h3>产品特点：</h3>
                        <ul>
                            <li>1、企业内部部署</li>
                            <li>2、支持android、ios</li>
                            <li>3、配套PC端后台管理</li>
                        </ul>
                    </li>
                    <li>
                        <h3>主要功能：</h3>
                        <ul>
                            <li>1、公告：PC端发布公告，手机端接收查看公告。</li>
                            <li>2、打卡：记录打卡时间、地点。</li>
                            <li>3、工作日报、工作周报：手机端填写日报和周报，主管在PC端可查看部门成员的日报及周报。</li>
                            <li>4、拜访：记录拜访内容，可拍照上传照片，记录定位信息。</li>
                            <li>5、商机：记录管理商机信息，设置商机成交概率。</li>
                            <li>6、客户、联系人：管理企业客户及联系人信息。</li>
                            <li>7、扫名片：扫名片的维码，保存为联系人信息。</li>
                        </ul>
                    </li>
                    <li>
                        <h3>权限管理：</h3>
                        <ul>
                            <li>APP主要用户为销售员和销售经理，销售员的权限范围为所有自己的记录，销售经理可查看管辖范围内的所有销售员的记录。</li>
                        </ul>
                    </li>
                    <li>
                        <h3>推荐服务器配置：</h3>
                        <ul>
                            <li>CPU：4核</li>
                            <li>内存：8G</li>
                            <li>硬盘：C盘100G，D盘400G</li>
                            <li>网络带宽：10M</li>
                            <li>操作系统：windows server 2012</li>
                            <li>数据库：SQL Server 2012</li>
                        </ul>
                    </li>

                </ul>
            </div>
            <div class="part_four">
                <h3>服务报价</h3>
                <div class="left phone">
                    <p>移动销售助手</p>
                </div>
                <span class="left equal"></span>
                <div class="left all">
                    <p class="price">￥20000.00</p>
                    <a href="/buy/buy_show.json?ID=204">去结算</a>

                </div>
                <div class="left" style="padding-top:88px;font-size:12px;color:#999999;">（具体价格联系客服更改）</div>
            </div>
            <div class="clear"></div>
            <div class="remarks">
                <p class="left">备注：</p>
                <ul class="left">
                    <li>50个客户端起</li>
                    <li>50—100个客户端</li>
                    <li>100个以上（无上限）</li>
                </ul>
                <ul class="left">
                    <li>第一年20000元含部署实施费用；第二年起每年收取15%服务费 </li>
                    <li>第一年35000元含部署实施费用；第二年起每年收取15%服务费 </li>
                    <li>第一年50000元含部署实施费用；第二年起每年收取15%服务费 </li>
                </ul>
            </div>
        </div>
    </div>
    <jsp:include page="foot.jsp"></jsp:include>

<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?f7000683e1a3d9f334072a297bb880ee";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>
</body>
</html>