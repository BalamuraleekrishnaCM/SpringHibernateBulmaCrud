<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Student Database!</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.2/css/bulma.min.css">

        <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>

        <script src="js/jquery.min.js"></script>

        <script>
            $(document).ready(function () {

                $.ajax({
                    url: "display1.htm",
                    type: "POST"

                });

                var isEditRow = false;

                var editId;
                $("#submit").click(function () {
                    var id;
                    if (!isEditRow)
                    {
                        id = 0;
                    } else
                    {
                        id = editId;

                    }
                    var name = $("#name").val();
                    var email = $("#email").val();
                    var phone = $("#phone").val();
                    // alert("Column ID "+id);
                    $.ajax({
                        url: "insert.htm",
                        type: "POST",
                        data: {
                            id: id,
                            name: name,
                            email: email,
                            phone: phone
                        },
                        success: function (data) {
                            //  alert(data);
                            view();
                            $("#name").val("");
                            $("#email").val("");
                            $("#phone").val("");
                            $(".insertData").attr('value', 'Submit');
                            isEditRow = false;

                        }
                    });
                });

                // Edit
                $("#tbody").on("click", ".editBtn", function () {
                    // alert("editing ");
                    var id = $(this).attr('eid');
                    editId = id;
                    // alert("Edit Column ID "+id)
                    isEditRow = true;
                    $.ajax({
                        url: "edit.htm",
                        type: "POST",
                        data: {
                            id: id,
                        },
                        success: function (data)
                        {
                            //alert(data);
                            var obj = JSON.parse(data);
                            $("#name").val(obj.name);
                            $("#email").val(obj.email);
                            $("#phone").val(obj.phone);
                            //$("#editId").val(obj.id);

                            $(".insertData").attr('value', 'Update');

                        }
                    });
                });




                //Delete
                $("#tbody").on("click", ".deleteBtn", function () {
                    var id = $(this).attr('did');
                    //alert("Delete?");
                    $.ajax({
                        url: "delete.htm",
                        type: "POST",
                        data: {
                            id: id
                        },
                        success: function (data) {
                            view();
                        }
                    });

                });
                //View            
                function view() {
                    $.post("display.htm", function (data) {
                        var obj = JSON.parse(data);
                        $("#tbody").empty();
                        for (i = 0; i < obj.length; i++)
                        {
                            $("#tbody").append('<tr><td>' + obj[i].name + '</td>\n\
                                        <td>' + obj[i].email + '</td>\n\
                                        <td>' + obj[i].phone + '</td>\n\
                                        <td><input type="submit" value="Edit" name="Edit"  class="editBtn button  is-info  is-small" name="Edit"  eid="' + obj[i].id + '"/> </td> \n\
                                        <td> <input type="submit" value="Delete" class="deleteBtn button is-danger  is-small" name="Delete"  did="' + obj[i].id + '" /> </td>  </tr>');
                        }
                    });
                }
                view();

            });
        </script>        
    </head>
    <body>

    <nav class="navbar" role="navigation" aria-label="main navigation">
        <div class="navbar-brand">
            <a class="navbar-item" href="https://bulma.io">
                <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
            </a>

            <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
            </a>
        </div>

        <div id="navbarBasicExample" class="navbar-menu">
            <div class="navbar-start">
                <a class="navbar-item" href="index.htm">
                    Home
                </a>

                <a class="navbar-item">
                    Registration
                </a>

            </div>

            <div class="navbar-end">
                <div class="navbar-item">
                    <div class="buttons">
                        <a class="button is-primary" href="https://baluwyne.wordpress.com/">
                            <strong>Contact Me</strong>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    
    
    
    <div class="tile is-ancestor">
  <div class="tile is-vertical is-8">
    
    <div class="tile is-parent">
      <article class="tile is-child notification is-danger">
        <p class="title">Registration</p>
        <p class="subtitle">registered users </p>
        <div class="content">
                    <div class="column">

                        <table class="table" style="background-color:  whitesmoke" >
                            <thead class="bd-notification  has-text-centered">
                                <tr>
                                    <th><abbr title="Name">Name</abbr></th>
                                    <th><abbr title="Email">Email</abbr></th>
                                    <th><abbr title="Phone">Phone</abbr></th>
                                </tr>
                            </thead>
                            <tbody id="tbody">
                            </tbody>
                        </table>
                    </div>
        </div>
      </article>
    </div>
  </div>
  <div class="tile is-parent">
    <article class="tile is-child notification is-success">
      <div class="content">
        <p class="title">New user</p>
        <p class="subtitle">Enter user details</p>
        <div class="content">
        <form id="myform" action="" method="post" modelAttribute="submit">
                            <div class="column " style="background-color:  whitesmoke">
                                <p class="has-text-centered has-text-notification "> Enter  Details</p>
                                <div class="field">
                                    <label class="label">Name</label>
                                    <div class="control">
                                        <input class="input" id ="name" type="text" placeholder="Name">
                                    </div>
                                </div>
                                <div class="field">
                                    <label class="label">Email</label>
                                    <div class="control">
                                        <input class="input" id="email" type="email" placeholder="abc@gmail.com">
                                    </div>
                                </div>
                                <div class="field">
                                    <label class="label">Phone</label>
                                    <div class="control">
                                        <input class="input" id = "phone" type="text" placeholder="123467890">
                                    </div>
                                </div>
                                <div class="field is-grouped ">
                                    <div class="is-centered">
                                        <p class="control">
                                            <input class="button insertData is-success" type="button" id="submit" value="submit">
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </form>
        </div>
      </div>
    </article>
  </div>
</div>

    
    
    
    
    
    
    
    
    
    
    
    
    
    
<!--    <section class="section">
        <div class="container"> 
                        <div class="columns notification is-primary has-text-centered">
                            <div class="column"> <a href="dynamic.htm">Dynamic Table</a></div>
                            <div class="column"><a href="test7.htm">Angular JS</a></div>
                        </div>
            <span><br>
            </span>
            <div class="row">
                <div class=" columns is-mobile is-centered">

                    <div class="column">

                        <table class="table" style="background-color:  whitesmoke" >
                            <thead class="bd-notification  has-text-centered">
                                <tr>
                                    <th><abbr title="Name">Name</abbr></th>
                                    <th><abbr title="Email">Email</abbr></th>
                                    <th><abbr title="Phone">Phone</abbr></th>
                                </tr>
                            </thead>
                            <tbody id="tbody">
                            </tbody>
                        </table>
                    </div>
                    <div class="column">
                        <form id="myform" action="" method="post" modelAttribute="submit">
                            <div class="column " style="background-color:  whitesmoke">
                                <p class="has-text-centered has-text-notification "> Enter  Details</p>
                                <div class="field">
                                    <label class="label">Name</label>
                                    <div class="control">
                                        <input class="input" id ="name" type="text" placeholder="Name">
                                    </div>
                                </div>
                                <div class="field">
                                    <label class="label">Email</label>
                                    <div class="control">
                                        <input class="input" id="email" type="email" placeholder="abc@gmail.com">
                                    </div>
                                </div>
                                <div class="field">
                                    <label class="label">Phone</label>
                                    <div class="control">
                                        <input class="input" id = "phone" type="text" placeholder="123467890">
                                    </div>
                                </div>
                                <div class="field is-grouped ">
                                    <div class="is-centered">
                                        <p class="control">
                                            <input class="button insertData is-success" type="button" id="submit" value="submit">
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>-->
</body>
</html>