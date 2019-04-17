<?php
$options = getopt("m:");
if(isset($options['m']))
{
    if(!empty($options['m']))
    {
        $movie_name=$options['m'];
        $json = file_get_contents("http://www.omdbapi.com/?t='".urlencode($movie_name)."'&apikey=8d6012");
        $data = json_decode($json);
        $Title=isset($data->Title)?$data->Title:'IMDB Rating not found';
        $imdbRating=isset($data->imdbRating)?$data->imdbRating:'IMDB Rating not found';
        $Ratings=isset($data->Ratings)?$data->Ratings:array();
        if(sizeof($Ratings) > 0)
        {
            $key = searcharray('Rotten Tomatoes',json_decode(json_encode($Ratings), true), 'Source');
        }
        $RT=isset($Ratings[$key]->Value)?$Ratings[$key]->Value:'Rotten Tomatoes Rating not found';
        echo 'Movie Name :'.$Title.PHP_EOL;
        echo 'Rotten Tomatoes :'.$RT.PHP_EOL;
        echo 'IMDB Rating :'.$imdbRating.PHP_EOL;
    }
    else
    {
        echo 'Movie name is empty';
    }
}
else
{
    echo 'Option -m not found ';
}

function searcharray($value, $array,$key) {
    foreach ($array as $k => $val) {
        if ($val[$key] == $value) {
            return $k;
        }
    }
    return null;
 }

?>
