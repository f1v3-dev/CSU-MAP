var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('.btn-file-delete').on('click', function () {
            _this.file_delete();
        })
    },
    save : function () {

        var formData = new FormData($('#form-data')[0]);

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            // enctype: 'multipart/form-data',
            data: formData,
            processData: false,
            contentType: false,
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            image: $('#image').val(),
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    file_delete : function () {
        if(confirm("정말로 삭제하시겠습니까?")){
            var fileId = $(this).data('file-id');
            // var fileName = $.trim($('.file-name').text()); // 이미지 파일 정보 가져오기
            var id = $('#id').val();

            var fileName = $.trim($('.file-name')[fileId].text())


            $.ajax({
                type: 'DELETE',
                url: '/api/v1/posts/file/' + id,
                processData: false,
                contentType: 'application/json',
                data: JSON.stringify(data)
            }).done(function() {
                alert('파일이 삭제되었습니다.');
                window.location.href = '/posts/update/' + id;
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }
};

main.init();