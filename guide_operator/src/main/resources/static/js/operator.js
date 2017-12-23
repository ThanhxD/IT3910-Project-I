
$(document).ready(function() {
    var max_fields      = 10; //maximum input boxes allowed
    var wrapper         = $(".input_fields_wrap"); //Fields wrapper
    var add_button      = $(".add_field_button"); //Add button ID
    var z               = $(".input_field_wrap > select")
    var x = 1; //initlal text box count
    $(add_button).click(function(e){ //on add input button click
        e.preventDefault();
        if(x < max_fields){ //max input box allowed
            x++; //text box increment
            $(wrapper).append('<div class = "input-margin"><div class="col-sm-8 col-sm-offset-3"><select name="location'+x+'" class="form-control"><option>Hà Nội</option><option>Hà Giang</option><option>Cao Bằng</option><option>Bắc Kạn</option><option>Tuyên Quang</option><option>Lào Cai</option><option>Điện Biên</option><option>Lai Châu</option><option>Sơn La</option><option>Yên Bái</option><option>Hòa Bình</option><option>Thái Nguyên</option><option>Lạng Sơn</option><option>Quảng Ninh</option><option>Bắc Giang</option><option>Phú Thọ</option><option>Vĩnh Phúc</option><option>Bắc Ninh</option><option>Hải Dương</option><option>Hải Phòng</option><option>Hưng Yên</option><option>Thái Bình</option><option>Hà Nam</option><option>Nam Định</option><option>Ninh Bình</option><option>Thanh Hóa</option><option>Nghệ An</option><option>Hà Tĩnh</option><option>Quảng Bình</option><option>Quảng Trị</option><option>Thừa Thiên Huế</option><option>Đà Nẵng</option><option>Quảng Nam</option><option>Quảng Ngãi</option><option>Bình Định</option><option>Phú Yên</option><option>Khánh Hòa</option><option>Ninh Thuận</option><option>Bình Thuận</option><option>Kon Tum</option><option>Gia Lai</option><option>Đắk Lắk</option><option>Đắk Nông</option><option>Lâm Đồng</option><option>Bình Phước</option><option>Tây Ninh</option><option>Bình Dương</option><option>Đồng Nai</option><option>Bà Rịa - Vũng Tàu</option><option>Hồ Chí Minh</option><option>Long An</option><option>Tiền Giang</option><option>Bến Tre</option><option>Trà Vinh</option><option>Vĩnh Long</option><option>Đồng Tháp</option><option>An Giang</option><option>Kiên Giang</option><option>Cần Thơ</option><option>Hậu Giang</option><option>Sóc Trăng</option><option>Bạc Liêu</option><option>Cà Mau</option></select></div><a href="#" class="remove_field btn btn-link"><span class="glyphicon glyphicon-minus"></span></a></div>'); //add input box <a href="#" class="remove_field ">X</a>
            $("#count-location").val(x);

        }
    });
    
    $(wrapper).on("click",".remove_field", function(e){ //user click on remove text
        e.preventDefault(); $(this).parent('div').remove(); x--;
        $("#count-location").val(x);
        $(".input-margin").remove();
        for(var i=2;i<=x;i++){
            $(wrapper).append('<div class = "input-margin"><div class="col-sm-8 col-sm-offset-3"><select name="location'+i+'" class="form-control"><option>Hà Nội</option><option>Hà Giang</option><option>Cao Bằng</option><option>Bắc Kạn</option><option>Tuyên Quang</option><option>Lào Cai</option><option>Điện Biên</option><option>Lai Châu</option><option>Sơn La</option><option>Yên Bái</option><option>Hòa Bình</option><option>Thái Nguyên</option><option>Lạng Sơn</option><option>Quảng Ninh</option><option>Bắc Giang</option><option>Phú Thọ</option><option>Vĩnh Phúc</option><option>Bắc Ninh</option><option>Hải Dương</option><option>Hải Phòng</option><option>Hưng Yên</option><option>Thái Bình</option><option>Hà Nam</option><option>Nam Định</option><option>Ninh Bình</option><option>Thanh Hóa</option><option>Nghệ An</option><option>Hà Tĩnh</option><option>Quảng Bình</option><option>Quảng Trị</option><option>Thừa Thiên Huế</option><option>Đà Nẵng</option><option>Quảng Nam</option><option>Quảng Ngãi</option><option>Bình Định</option><option>Phú Yên</option><option>Khánh Hòa</option><option>Ninh Thuận</option><option>Bình Thuận</option><option>Kon Tum</option><option>Gia Lai</option><option>Đắk Lắk</option><option>Đắk Nông</option><option>Lâm Đồng</option><option>Bình Phước</option><option>Tây Ninh</option><option>Bình Dương</option><option>Đồng Nai</option><option>Bà Rịa - Vũng Tàu</option><option>Hồ Chí Minh</option><option>Long An</option><option>Tiền Giang</option><option>Bến Tre</option><option>Trà Vinh</option><option>Vĩnh Long</option><option>Đồng Tháp</option><option>An Giang</option><option>Kiên Giang</option><option>Cần Thơ</option><option>Hậu Giang</option><option>Sóc Trăng</option><option>Bạc Liêu</option><option>Cà Mau</option></select></div><a href="#" class="remove_field btn btn-link"><span class="glyphicon glyphicon-minus"></span></a></div>');
        }
    })
});