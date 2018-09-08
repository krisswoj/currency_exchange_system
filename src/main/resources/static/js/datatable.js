$(document).ready(function () {
    var table = $('#ingredientsTable').DataTable({


        "sAjaxSource": "/ingredients-list-all",
        "sAjaxDataProp": "",
        "aoColumns": [
            {
                "mData": "id",
                "mRender": function (data, type, full) {
                    return '<input type="hidden" name="food_ingredient_id" value="' + data + '">' + data + ''
                }
            },
            {"mData": "name"},
            {"mData": "description"},
            {"mData": "category"},
            {"mData": "amountProtins"},
            {"mData": "amountCarbs"},
            {"mData": "amountFats"},
            {
                "mData": null,
                "mRender": function (data, type, full) {
                    return '<input name="gramsPortion" value="0">'
                }
            }
        ],
        "oLanguage": {
            "sProcessing": "Dane są przetwarzane...",
            "sLengthMenu": "Ilość pozycji na stronie _MENU_",
            "sZeroRecords": "Brak danych do wyświetlenia",
            "sInfo": "Pozycja _START_ z _END_ na _TOTAL_",
            "sInfoEmpty": "Strona 0 na 0 na wpisów  0",
            "sInfoFiltered": "(Wyfiltrowano z  _MAX_ rekordów)",
            "sInfoPostFix": "",
            "sSearch": "Szukaj:",
            "sUrl": "",
            "oPaginate": {
                "sFirst": "Pierwsza",
                "sPrevious": "Cofnij",
                "sNext": "Dalej",
                "sLast": "Cofnij"
            }
        },

        initComplete: function () {
            this.api().columns().every(function () {
                var column = table.column('3');
                var select = $('<select><option value=""></option></select>')
                    .appendTo($(column.footer()).empty())
                    .on('change', function () {
                        var val = $.fn.dataTable.util.escapeRegex(
                            $(this).val()
                        );

                        column
                            .search(val ? '^' + val + '$' : '', true, false)
                            .draw()
                    });
                column.data().unique().sort().each(function (d, j) {
                    select.append('<option value="' + d + '">' + d + '</option>')
                });
            });

            // // Handle form submission event
            // $('#frm-ingredientsTable').on('submit', function (e) {
            //     var form = this;
            //
            //     // Encode a set of form elements from all pages as an array of names and values
            //     var params = table.$('input,select,textarea').serializeArray();
            //
            //     // Iterate over all form elements
            //     $.each(params, function () {
            //         // If element doesn't exist in DOM
            //         if (!$.contains(document, form[this.name])) {
            //             // Create a hidden element
            //             $(form).append(
            //                 $('<input>')
            //                     .attr('type', 'hidden')
            //                     .attr('name', this.name)
            //                     .val(this.value)
            //             );
            //         }
            //     })
            // });

            // $('button').click( function() {
            //     var data = table.$('input, select').serialize();
            //     alert(
            //         "The following data would have been submitted to the server: \n\n"+
            //         data.substr( 0, 120 )+'...'
            //     );
            //     return false;
            // } );

        }

    })
});



// "mRender": function (data, type, full) {
//     if (data == 1) {
//         return "szynka"
//     }
//     if (data == 2) {
//         return "Makaron"
//     }
//     if (data == 3) {
//         return "Boczek"
//     }
//     if (data == 4) {
//         return "Wolowina"
//     }
//     if (data == 5) {
//         return "Cukieri"
//     }
//     if (data == 6) {
//         return "Sol"
//     }
// }
// próby przetłumaczenia, o ile byłem wstanie to zrobić dla pozycji z tablicy to w przypadku rozsuwanego selecta poniżej
// system zassyłał oryginalne wartości z bazy co finalnie uniemożliwiało sorotwanie tabeli.