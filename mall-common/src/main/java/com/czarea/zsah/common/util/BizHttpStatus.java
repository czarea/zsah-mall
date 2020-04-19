package com.czarea.zsah.common.util;

/**
 * 业务异常状态实体
 * 把HttpStatus的所有异常都拷贝到这里，需要自定义的可以在todo下面自定义
 *
 * @author qinminghui
 */
public enum BizHttpStatus {

    /**
     * {@code 100 Continue}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.2.1">HTTP/1.1: Semantics and Content, section 6.2.1</a>
     */
    CONTINUE(100, "Continue"),
    /**
     * {@code 101 Switching Protocols}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.2.2">HTTP/1.1: Semantics and Content, section 6.2.2</a>
     */
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    /**
     * {@code 102 Processing}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc2518#section-10.1">WebDAV</a>
     */
    PROCESSING(102, "Processing"),
    /**
     * {@code 103 Checkpoint}.
     *
     * @see <a href="http://code.google.com/p/gears/wiki/ResumableHttpRequestsProposal">A proposal for supporting
     * resumable POST/PUT HTTP requests in HTTP/1.0</a>
     */
    CHECKPOINT(103, "Checkpoint"),

    // 2xx Success

    /**
     * {@code 200 OK}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.1">HTTP/1.1: Semantics and Content, section 6.3.1</a>
     */
    OK(200, "OK"),
    /**
     * {@code 201 Created}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.2">HTTP/1.1: Semantics and Content, section 6.3.2</a>
     */
    CREATED(201, "Created"),
    /**
     * {@code 202 Accepted}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.3">HTTP/1.1: Semantics and Content, section 6.3.3</a>
     */
    ACCEPTED(202, "Accepted"),
    /**
     * {@code 203 Non-Authoritative Information}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.4">HTTP/1.1: Semantics and Content, section 6.3.4</a>
     */
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    /**
     * {@code 204 No Content}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.5">HTTP/1.1: Semantics and Content, section 6.3.5</a>
     */
    NO_CONTENT(204, "No Content"),
    /**
     * {@code 205 Reset Content}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.3.6">HTTP/1.1: Semantics and Content, section 6.3.6</a>
     */
    RESET_CONTENT(205, "Reset Content"),
    /**
     * {@code 206 Partial Content}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7233#section-4.1">HTTP/1.1: Range Requests, section 4.1</a>
     */
    PARTIAL_CONTENT(206, "Partial Content"),
    /**
     * {@code 207 Multi-Status}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-13">WebDAV</a>
     */
    MULTI_STATUS(207, "Multi-Status"),
    /**
     * {@code 208 Already Reported}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc5842#section-7.1">WebDAV Binding Extensions</a>
     */
    ALREADY_REPORTED(208, "Already Reported"),
    /**
     * {@code 226 IM Used}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc3229#section-10.4.1">Delta encoding in HTTP</a>
     */
    IM_USED(226, "IM Used"),

    // 3xx Redirection

    /**
     * {@code 300 Multiple Choices}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.1">HTTP/1.1: Semantics and Content, section 6.4.1</a>
     */
    MULTIPLE_CHOICES(300, "Multiple Choices"),
    /**
     * {@code 301 Moved Permanently}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.2">HTTP/1.1: Semantics and Content, section 6.4.2</a>
     */
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    /**
     * {@code 302 Found}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.3">HTTP/1.1: Semantics and Content, section 6.4.3</a>
     */
    FOUND(302, "Found"),
    /**
     * {@code 302 Moved Temporarily}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc1945#section-9.3">HTTP/1.0, section 9.3</a>
     * @deprecated in favor of {@link #FOUND} which will be returned from {@code HttpStatus.valueOf(302)}
     */
    @Deprecated
    MOVED_TEMPORARILY(302, "Moved Temporarily"),
    /**
     * {@code 303 See Other}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.4">HTTP/1.1: Semantics and Content, section 6.4.4</a>
     */
    SEE_OTHER(303, "See Other"),
    /**
     * {@code 304 Not Modified}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7232#section-4.1">HTTP/1.1: Conditional Requests, section 4.1</a>
     */
    NOT_MODIFIED(304, "Not Modified"),
    /**
     * {@code 307 Temporary Redirect}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.4.7">HTTP/1.1: Semantics and Content, section 6.4.7</a>
     */
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    /**
     * {@code 308 Permanent Redirect}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7238">RFC 7238</a>
     */
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    // --- 4xx Client Error ---

    /**
     * {@code 400 Bad Request}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.1">HTTP/1.1: Semantics and Content, section 6.5.1</a>
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * {@code 401 Unauthorized}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7235#section-3.1">HTTP/1.1: Authentication, section 3.1</a>
     */
    UNAUTHORIZED(401, "Unauthorized"),
    /**
     * {@code 402 Payment Required}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.2">HTTP/1.1: Semantics and Content, section 6.5.2</a>
     */
    PAYMENT_REQUIRED(402, "Payment Required"),
    /**
     * {@code 403 Forbidden}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.3">HTTP/1.1: Semantics and Content, section 6.5.3</a>
     */
    FORBIDDEN(403, "Forbidden"),
    /**
     * {@code 404 Not Found}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.4">HTTP/1.1: Semantics and Content, section 6.5.4</a>
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * {@code 405 Method Not Allowed}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.5">HTTP/1.1: Semantics and Content, section 6.5.5</a>
     */
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    /**
     * {@code 406 Not Acceptable}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.6">HTTP/1.1: Semantics and Content, section 6.5.6</a>
     */
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    /**
     * {@code 407 Proxy Authentication Required}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7235#section-3.2">HTTP/1.1: Authentication, section 3.2</a>
     */
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    /**
     * {@code 408 Request Timeout}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.7">HTTP/1.1: Semantics and Content, section 6.5.7</a>
     */
    REQUEST_TIMEOUT(408, "Request Timeout"),
    /**
     * {@code 409 Conflict}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.8">HTTP/1.1: Semantics and Content, section 6.5.8</a>
     */
    CONFLICT(409, "Conflict"),
    /**
     * {@code 410 Gone}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.9">
     * HTTP/1.1: Semantics and Content, section 6.5.9</a>
     */
    GONE(410, "Gone"),
    /**
     * {@code 411 Length Required}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.10">
     * HTTP/1.1: Semantics and Content, section 6.5.10</a>
     */
    LENGTH_REQUIRED(411, "Length Required"),
    /**
     * {@code 412 Precondition failed}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7232#section-4.2">
     * HTTP/1.1: Conditional Requests, section 4.2</a>
     */
    PRECONDITION_FAILED(412, "Precondition Failed"),
    /**
     * {@code 413 Payload Too Large}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.11">
     * HTTP/1.1: Semantics and Content, section 6.5.11</a>
     * @since 4.1
     */
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    /**
     * {@code 414 URI Too Long}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.12">
     * HTTP/1.1: Semantics and Content, section 6.5.12</a>
     * @since 4.1
     */
    URI_TOO_LONG(414, "URI Too Long"),
    /**
     * {@code 415 Unsupported Media Type}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.13">
     * HTTP/1.1: Semantics and Content, section 6.5.13</a>
     */
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    /**
     * {@code 416 Requested Range Not Satisfiable}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7233#section-4.4">HTTP/1.1: Range Requests, section 4.4</a>
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
    /**
     * {@code 417 Expectation Failed}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.5.14">
     * HTTP/1.1: Semantics and Content, section 6.5.14</a>
     */
    EXPECTATION_FAILED(417, "Expectation Failed"),
    /**
     * {@code 418 I'm a teapot}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc2324#section-2.3.2">HTCPCP/1.0</a>
     */
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    /**
     * {@code 422 Unprocessable Entity}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-11.2">WebDAV</a>
     */
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    /**
     * {@code 423 Locked}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-11.3">WebDAV</a>
     */
    LOCKED(423, "Locked"),
    /**
     * {@code 424 Failed Dependency}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-11.4">WebDAV</a>
     */
    FAILED_DEPENDENCY(424, "Failed Dependency"),
    /**
     * {@code 426 Upgrade Required}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc2817#section-6">Upgrading to TLS Within HTTP/1.1</a>
     */
    UPGRADE_REQUIRED(426, "Upgrade Required"),
    /**
     * {@code 428 Precondition Required}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6585#section-3">Additional HTTP Status Codes</a>
     */
    PRECONDITION_REQUIRED(428, "Precondition Required"),
    /**
     * {@code 429 Too Many Requests}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6585#section-4">Additional HTTP Status Codes</a>
     */
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    /**
     * {@code 431 Request Header Fields Too Large}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6585#section-5">Additional HTTP Status Codes</a>
     */
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    /**
     * {@code 451 Unavailable For Legal Reasons}.
     *
     * @see <a href="https://tools.ietf.org/html/draft-ietf-httpbis-legally-restricted-status-04">
     * An HTTP Status Code to Report Legal Obstacles</a>
     * @since 4.3
     */
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    // --- 5xx Server Error ---

    /**
     * {@code 500 Internal Server Error}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.1">HTTP/1.1: Semantics and Content, section 6.6.1</a>
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    /**
     * {@code 501 Not Implemented}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.2">HTTP/1.1: Semantics and Content, section 6.6.2</a>
     */
    NOT_IMPLEMENTED(501, "Not Implemented"),
    /**
     * {@code 502 Bad Gateway}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.3">HTTP/1.1: Semantics and Content, section 6.6.3</a>
     */
    BAD_GATEWAY(502, "Bad Gateway"),
    /**
     * {@code 503 Service Unavailable}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.4">HTTP/1.1: Semantics and Content, section 6.6.4</a>
     */
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    /**
     * {@code 504 Gateway Timeout}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.5">HTTP/1.1: Semantics and Content, section 6.6.5</a>
     */
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    /**
     * {@code 505 HTTP Version Not Supported}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc7231#section-6.6.6">HTTP/1.1: Semantics and Content, section 6.6.6</a>
     */
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),
    /**
     * {@code 506 Variant Also Negotiates}
     *
     * @see <a href="http://tools.ietf.org/html/rfc2295#section-8.1">Transparent Content Negotiation</a>
     */
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    /**
     * {@code 507 Insufficient Storage}
     *
     * @see <a href="http://tools.ietf.org/html/rfc4918#section-11.5">WebDAV</a>
     */
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
    /**
     * {@code 508 Loop Detected}
     *
     * @see <a href="http://tools.ietf.org/html/rfc5842#section-7.2">WebDAV Binding Extensions</a>
     */
    LOOP_DETECTED(508, "Loop Detected"),
    /**
     * {@code 509 Bandwidth Limit Exceeded}
     */
    BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"),
    /**
     * {@code 510 Not Extended}
     *
     * @see <a href="http://tools.ietf.org/html/rfc2774#section-7">HTTP Extension Framework</a>
     */
    NOT_EXTENDED(510, "Not Extended"),
    /**
     * {@code 511 Network Authentication Required}.
     *
     * @see <a href="http://tools.ietf.org/html/rfc6585#section-6">Additional HTTP Status Codes</a>
     */
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required"),
    /**
     * mchId不能为空
     */
    MCHID_REQUIRED(600, "MchId Required"),
    /**
     * 角色被绑定
     */
    ROLE_BIND_ADMIN(601, "Role Bound"),
    /**
     * 账号或者密码错误
     */
    ACCOUNT_PASSWORD_NOT_MATCH(602, "Account Or Password  Not Match"),
    /**
     * 旧密码输入错误
     */
    OLD_PAWWORD_ERROR(603, "Old Password Error"),
    /**
     * 用户不存在
     */
    USER_NOT_EXISTED(604, "User Not Exist"),
    /**
     * 管理员账号冲突
     */
    ADMIN_ACCOUNT_EXIST(605, "Admin Account Exist"),
    /**
     * 商户配置错误，导致小程序登录错误
     */
    MCH_PRO_CONFIG_LOGIN_ERROR(606, "Pro Login Error Cause Mch Config"),
    /**
     * 商户的微信小程序code错误、无效
     */
    MCH_PRO_CODE_ERROR(607, "Pro Code Error"),
    /**
     * 商户id加密错误
     */
    MCH_ID_ENCRYPT_ERROR(608, "Mch Id Encrypt Error"),
    /**
     * 商户id解密错误
     */
    MCH_ID_DENCRYPT_ERROR(609, "Mch Id Dencrypt Error"),
    /**
     * 手机号码已存在
     */
    PHONE_IS_EXIST(610, "Phone Is Exist"),
    /**
     * 验证码错误
     */
    VERIFICATION_CODE_ERROR(611, "Verification Code Error"),
    /**
     * 未绑定主体到微信开放平台
     */
    NOT_BIND_WECHAT_OPEN_PLATFORM(612, "Not Bind Wechat Open Platform"),
    /**
     * 小程序解密失败
     */
    PRO_DECRYPT_FAILURE(613, "Pro Decrypt Failure"),
    /**
     * 商户微信公众号配置为空
     */
    MCH_WXPUBLIC_CONFIG_IS_NULL(614, "WxPublic Config Is Null"),
    /**
     * 商户微信公众号获取jsSdk授权错误
     */
    MCH_WXPUBLIC_JSSDK_ACCESS_ERROR(615, "WxPublic JsSdk Access Error"),
    /**
     * id加密错误
     */
    ID_ENCRYPT_ERROR(616, "Id Encrypt Error"),
    /**
     * id解密错误
     */
    ID_DENCRYPT_ERROR(617, "Id Dencrypt Error"),
    /**
     * 景区未上线
     */
    SCENIC_NOT_ONLINE(618, "Scenic Not Online"),
    /**
     * 微信配置为空
     */
    WECHAT_CONFIG_IS_NUlL(619, "Wechat Config is Null"),
    /**
     * 用户拥有永久权益，无需购买
     */
    HAD_FOREVER_NOT_BUY(620, "Had Forever Not Buy"),
    /**
     * 景区免费
     */
    SCENIC_FREE(621, "Scenic Free"),
    /**
     * 账号被禁用
     */
    ACCOUNT_FORBIDDEN(622, "Account Forbidden"),
    /**
     * 极验验证错误
     */
    GEETEST_CHECK_ERROR(623, "Geetest Check Error"),
    /**
     * 账号不存在
     */
    ACCOUNT_NOT_EXIST(624, "Account Not Exist"),
    /**
     * 密码错误
     */
    PASSWORD_ERROR(625, "Password Error"),
    /**
     * 输入密码错误次数超过限制
     */
    PASSWORD_ERROR_TIMES_TOO_MUCH(626, "Password Error Times Too Much"),
    /**
     * 上级岗位不存在
     */
    PARENT_POST_NOT_EXIST(627, "Parent Post Not Exist"),
    /**
     * 商户已存在
     */
    MERCHANT_EXIST(628, "Merchant Exist"),
    /**
     * 合同已过期
     */
    MERCHANT_CONTRACT_EXPIRED(629, "Merchant Contract Expired"),
    /**
     * 名称重复
     */
    NAME_EXIST(630, "Name Exist"),
    /**
     * 无效激活码
     */
    INVALID_ACTIVATE_CODE(631, "Invalid Activate Code"),
    /**
     * 激活码已使用
     */
    ACTIVATE_CODE_USED(632, "Activate Code Used"),
    /**
     * 权益过期
     */
    INTERESTS_OUT_OF_DATE(633, "Interests Out Of Date"),
    /**
     * 当前卡版本错误
     */
    CARD_TYPE_ERROR(634, "Current card type error"),
    /**
     * 您已激活
     */
    CARD_HAVE_ACTIVATED(635, "Card Has Activated"),
    /**
     * 卡版本和激活码不对应
     */
    CARD_CODE_NOT_MATCH(636, "Card Code Not Match"),
    /**
     * 导游未提交审核记录
     */
    GUIDE_NOT_SUBMIT_AUDIT(637, "Guide Not Submit Audit"),
    /**
     * 导游审核已被处理
     */
    GUIDE_AUDIT_HAS_BEEN_PROCESSED(638, "Guide Audit Has Been Processed"),
    /**
     * 地接社名称已存在
     */
    AGENCY_NAME_EXIST(639, "Agency Name Exist"),
    /**
     * 路线名称已存在
     */
    ROUTE_NAME_EXIST(640, "Route Name Exist"),
    /**
     * 旅行团名称已存在
     */
    GROUP_NAME_EXIST(641, "Group Name Exist"),
    /**
     * 不能为当前账号
     */
    NOT_CURRENT_ACCOUNT(642, "Not Current Account"),
    /**
     * 地接社已绑定此导游
     */
    AGENCY_GUIDE_HAS_BINDING(643, "Agency Guide Has Binding"),
    /**
     * 导游出行时间存在交集
     */
    GUIDE_PLAYTIME_EXIST(644, "Guide Playtime Exist"),
    /**
     * 手机号码不存在
     */
    PHONE_IS_NOT_EXIST(645, "Phone Is Not Exist"),
    /**
     * 手机号码不存在 verifyRandomNum
     */
    VERIFY_RANDOM_NUM_IS_ERROR(646, "Verify Random Num Is Error"),
    /**
     * 已提交，请等待审核
     */
    SUBMITTED_FOR_REVIEW(647, "Submitted For Review "),
    /**
     * 已审核通过
     */
    HAS_BEEN_APPROVED(648, "Has Been Approved"),
    /**
     * 消息已经被确认
     */
    MESSAGE_HAS_CONFIRM(649, "Message Has Confirm"),
    /**
     * 当前游客已经参团
     */
    TOURIST_PLAYTIME_HAS_TRAVEL(650, "Tourist PlayTime Has Travel"),
    /**
     * 用户已绑定手机号码
     */
    USER_HAS_BEEN_BINDING_PHONE(651, "User Has Been Binding Phone"),
    /**
     * 非小程序用户
     */
    NON_WX_PRO_USER(652, "Non Wx Pro User"),
    /**
     * 旅行团不可编辑
     */
    GROUP_CAN_NOT_EDIT(653, "Group Can Not Edit"),
    /**
     * 只有出团中才获取组团的邀请小程序码
     */
    GROUP_INVITATION_CODE_ONLY_PALY(654, "Group Invitation Code Only Play"),
    /**
     * 地接社已经被冻结
     */
    AGENCY_ACTIVE_STATUS_BE_FROZEN(655, "Agency Active Status Be Frozen"),
    /**
     * 创建小程序码错误
     */
    CREATE_SMALL_PRO_CODE_ERROR(656, "Create Small Pro Code Error"),
    /**
     * 该导游已经分配了当前旅行团
     */
    GUIDE_HAS_BIND_THIS_GROUP(657, "Guide Has Bind This Group"),
    /**
     * 该导游的旅行团正在出行，不能解除
     */
    GUIDE_HAS_GROUP_IS_ON_AND_NOT_CANCEL(658, "The Tour Guide's Group Is On The Move And Cannot Be Cancelled"),
    /**
     * 该导游有待出行旅行团，解除导游绑定，则带出行旅行团变更为待分配
     */
    GUIDE_HAS_GROUP_TO_TRAVEL(659, "The Guide Has Group To Travel"),
    /**
     * 导游证号已经注册
     */
    GUIDE_CARD_NUMBER_HAS_BEEN_REGISTERED(660, "Guide Crad Number Has Been Registered"),
    /**
     * 身份证号已注册
     */
    ID_NUMBER_REGISTERED(661, "Id Number Registered"),
    /**
     * 身份证号码不合法
     */
    ID_NUMBER_IS_NOT_VALIDITY(662, "Id Number Is Not Validity"),
    /**
     * 主键缺失
     */
    ID_REQUIRED(663, "Id Is Required"),
    /**
     * 存在绑定的商户
     */
    BOUND_MERCHANTS_EXIST(664, "There Are Bound Merchants"),
    /**
     * excel 解析失败
     */
    EXCEL_PARSING_FAILED(665, "Excel Parsing Failed"),
    /**
     * 联系人不存在
     */
    LINKMAN_NOT_EXIST(666, "Linkman Not Exist"),
    /**
     * 用户已绑定其他手环
     */
    HAS_BEEN_BIND_ANOTHER_BAND(667, "Has Been Bind Another Band"),
    /**
     * 手环已被其他人绑定
     */
    BAND_IS_ALREADY_BOUND(668, "Band Is Already Bound"),
    /**
     * 设置紧急联系人达到上限
     */
    LIMIT_HAS_BEEN_REACHED(669, "Limit Has Been Reached"),
    /**
     * 创建二维码失败
     */
    CREATE_QRCODE_FAILURE(670, "Create Qrcode failure"),
    /**
     * app版本号已存在
     */
    APP_VERSION_NO_EXIST(671, "App VersionNo Exist"),
    /**
     * 景区app账号已经存在
     */
    SCENIC_ACCOUNT_EXIST(672, "Scenic Account Exist"),
    /**
     * 景区已经存在
     */
    SCENIC_NAME_EXIST(673, "Scenic Name Exist"),
    /**
     * 景区票务已经存在
     */
    TICKET_NAME_EXIST(674, "Ticket Name Exist"),
    /**
     * 景区二维码不存在
     */
    SCENIC_QRCODE_NO_EXIST(675, "Scenic qrCode No Exist"),
    /**
     * 订单未支付
     */
    ORDER_STATUS_NO_PAY(676, "Order Status No Pay"),
    /**
     * 订单不存在
     */
    ORDER_IS_NOT_EXIST(677, "Order Is Not Exist"),
    /**
     * 订单价格错误
     */
    ORDER_TOTAL_PRICE_IS_ERROR(678, "Order Total Price is Error"),
    /**
     * 角色已绑定账号，请解除绑定后再删除
     */
    ROLE_NOT_DELETE_DUE_BIND_ACCOUNT(679, "Role Not Delete Due Bind Account"),
    /**
     * 绑定紧急联系人失败
     */
    BIND_LINKMAN_IS_ERROR(680, "Bind Linkman Is Error"),
    /**
     * 购票数量未超过最低数量
     */
    TICKET_NOT_EXCEEDING_MINIMUM(681, "Ticket Not Exceeding The Minimum Purchase Quantity"),
    /**
     * 公众号授权登录失败
     */
    WXPUBLIC_AUTHORIZE_LOGIN_ERROR(682, "WxPublic Authorize Login Error"),
    /**
     * 分销商代理产品已存在
     */
    AGENT_PRODUCT_ALREADY_EXIST(683, "Agent Product Already Exists"),
    /**
     * 分销商已存在
     */
    DISTRIBUTOR_ALREADY_EXIST(684, "Distributor Already Exist"),
    /**
     * 分销商存在下级不能删除
     */
    DISTRIBUTORS_ALREADY_HAVE_SUBORDINATES(685, "Distributors Already Have Subordinates"),
    /**
     * 当前分销商已经产生订单
     */
    DISTRIBUTORS_ALREADY_HAS_AN_ORDER(686, " Distributors Already Has An Order"),
    /**
     * 入园码不存在
     */
    IN_CODE_NOT_EXIST(687, "In Code Not Exist"),
    /**
     * 订单不可退款
     */
    ORDER_NOT_REFUND(688, "Order Not Refund"),
    /**
     * 退款申请错误
     */
    REFUND_APPLY_ERROR(689, "Refund Apply Error"),
    /**
     * 门票退款数据为空
     */
    TICKET_REFUND_DATA_NULL(690, "Ticket Refund Data Null"),
    /**
     * 退票类型不存在
     */
    REFUND_TYPE_NOT_EXIST(691, "Refund Type Not Exist"),
    /**
     * 申请退款数据为空
     */
    APPLY_REFUND_DATA_NULL(692, "Apply Refund Data Null"),
    /**
     * 提交退款门票数据错误
     */
    SUBMIT_REFUND_TICKET_DATA_ERROR(693, "Submit Refund Ticket Data Error"),
    /**
     * 提交的退票数不能为0
     */
    SUBMIT_REFUND_TICKER_NUM_NOT_ZERO(694, "Submit Refund Ticket Not Zero"),
    /**
     * 退款申请中，请稍后操作
     */
    REFUND_APPLY_IN_PROGRESS_PLEASE_PROCEED_LATER(695, "Refund application in progress, please proceed later"),
    /**
     * 可退票数为0，无法退款
     */
    REFUNDABLE_NUM_IS_ZERO_NO_REFUND(696, "The number of refundable tickets is 0 and no refund is possible"),
    /**
     * 提交退票数大于可退票数，退款失败
     */
    SUBMIT_REFUND_NUM_LT_REFUNDABLE_NUM_ERROR(697, "If the number of refundable tickets is higher than the number of " +
            "refundable tickets, the refund fails"),
    /**
     * 可退团体票数不足，无法退款
     */
    REFUNDABLE_GROUP_NUM_NOT_ENOUGH_NO_REFUND(698, "Refundable groups do not have enough votes to be refunded"),
    /**
     * apk文件没上传
     */
    APK_NOT_UPLOAD(699, "Apk File Not Upload"),
    /**
     * 卡已经被订单绑定
     */
    CARD_AND_ORDER_BIND(700, "Card And Order Bind"),
    /**
     * 角色已经被删除
     */
    ROLE_DELETED(701, "Role Deleted"),
    /**
     * 验票数量与可用数量不符
     */
    INPUTNUM_NOT_EQUAL_CHECKNUM(702, "InputNum Not Equal checkNum"),
    /**
     * 分销商代理产品不存在
     */
    AGENT_PRODUCT_NOT_EXIST(703, "Agent Product Not Exists"),
    /**
     * 权益存在无法删除卡片
     */
    INTEREST_EXIST_NOT_DELETE_CARD(704, "Interest Exist Not Delete Card"),
    /**
     * 产品sku不存在
     */
    CARD_TYPE_SKU_NOT_EXIST(705, "Card Type Sku Not Exist"),
    /**
     * 内容已存在
     */
    CONTENT_TYPE_EXIST(706, "Content Type Already Exist"),
    /**
     * 当前卡片没有剩余权益
     */
    CURRENT_CARD_HAS_NO_RESIDUAL_INTEREST(707, "The Current Card Has No Residual Interest"),
    /**
     * 激活码权益不存在
     */
    ACTIVATE_CODE_INTERESTS_NOT_EXIST(708, "Activate Code Interests Not Exist"),
    /**
     * 已拥有权益
     */
    INTEREST_ALREADY_HAVE(709, "Interest Already Have"),
    /**
     * 无法领取自己分享的权益
     */
    UNABLE_RECEIVE_MY_SELT_SHARE(710, "Unable To Receive Their Own Share Of The Interest"),
    /**
     * 领取好友权益失败
     */
    RECEIVE_SHARE_INTEREST_FAILURE(711, "Receive Share Interest Failure"),
    /**
     * 导出数据超出指定数量
     */
    EXPORT_DATA_EXCEEDS_THE_LIMIT(712, "Export Data Exceeds The Limit"),
    /**
     * 上级分销商不能为当前分销商
     */
    PARENT_DISTRIBUTOR_CANNOTBE_THE_CURRENT_DISTRIBUTOR(713, "The Parent Distributor Cannot Be The Current " +
            "Distributor"),
    /**
     * 分销商账号已经存在
     */
    DISTRIBUTOR_ACCOUNT_ALREADY_EXIST(714, "Distributor Account Already Exist"),
    /**
     * 下单频率过快，请稍后再试
     */
    ORDER_TOO_FAST_PLEASE_TRY_AGAIN_LATER(715, "Order Too Fast, Please Try Again Later"),
    /**
     * 每种权益下单数最多20
     */
    MAX_NUMBER_OF_SINGALS_FOR_EACH_INTEREST_IS_20(716, "The Maximum Number Of Singals For Each Interest Is 20"),
    /**
     * 导览购买时长配置超过限制
     */
    CARDTYPE_SKU_CONFIGURATION_OVER_LIMIT(717, "Cardtype Sku Configuration Over Limit"),
    /**
     * 该分销商已经添加了该类产品
     */
    DISTRIBUTOR_HAS_ADDED_THE_PRODUCT(718, " Distributor Has Added The Product"),
    /**
     * 无法删除
     */
    UNABLE_TO_DELETE(719, " Unable To Delete"),
    UNFOLLOW_ALIPAY(720, "Unfollow Alipay"),
    UNCONFIRM_WX_MSG(721, "Unconfirm Wx Msg"),
    /**
     * 微信小程序登录code必填
     */
    WECHAT_PRO_LOGIN_CODE_REQUIRED(722, "Wechat Pro Login Code Required"),
    /**
     * 下单限制
     */
    ORDER_TO_LIMIT(723, "Order To Limit"),
    /**
     * 存在代理产品不能删除
     */
    DISTRIBUTOR_PRESENCE_PRODUCT_CANNOT_BE_DELETED(724, "Distributor Presence Product Cannot Be Deleted"),
    /**
     * 详情不能为空
     */
    DETAIL_IS_NOT_NULL(725,"Detail Is Not Null"),
    /**
     * 详情必须拥有中文（简体）
     */
    DETAIL_MUST_HAVE_CN(726,"Detail Must Have CN"),
    /**
     * 详情长度最多为3
     */
    DETAIL_MAX_LENGTH_IS_3(727,"Detail Max Length Is 3");
    /**
     * 添加自定义的异常状态
     */
    private final int value;

    private final String reasonPhrase;


    BizHttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }


    /**
     * Return the integer value of this status code.
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the reason phrase of this status code.
     */
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public static BizHttpStatus convert(int value) {
        for (BizHttpStatus status : BizHttpStatus.values()) {
            if (status.value == value) {
                return status;
            }
        }

        return BizHttpStatus.INTERNAL_SERVER_ERROR;
    }
}
