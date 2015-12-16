<?php

/**
 * This is the model class for table "m_nusrt".
 *
 * The followings are the available columns in table 'm_nusrt':
 * @property string $kode_prov
 * @property string $kode_kab
 * @property string $kode_kec
 * @property string $kode_desa
 * @property string $nbs
 * @property string $nusrt
 * @property string $nama_krt
 * @property string $alamat
 * @property string $tipe_kues
 * @property integer $kode_tanaman
 *
 * The followings are the available model relations:
 * @property TRt[] $tRts
 * @property TRt[] $tRts1
 * @property TRt[] $tRts2
 * @property TRt[] $tRts3
 * @property TRt[] $tRts4
 * @property TRt[] $tRts5
 * @property MBs $kodeDesa
 * @property MBs $kodeKab
 * @property MBs $kodeKec
 * @property MBs $kodeProv
 * @property MBs $nbs0
 * @property MTanaman $kodeTanaman
 */
class MNusrt extends CActiveRecord
{
	/**
	 * Returns the static model of the specified AR class.
	 * @param string $className active record class name.
	 * @return MNusrt the static model class
	 */
	public static function model($className=__CLASS__)
	{
		return parent::model($className);
	}

	/**
	 * @return string the associated database table name
	 */
	public function tableName()
	{
		return 'm_nusrt';
	}

	/**
	 * @return array validation rules for model attributes.
	 */
	public function rules()
	{
		// NOTE: you should only define rules for those attributes that
		// will receive user inputs.
		return array(
			array('kode_prov, kode_kab, kode_kec, kode_desa, nbs, nusrt, nama_krt', 'required'),
			array('kode_tanaman', 'numerical', 'integerOnly'=>true),
			array('kode_prov, kode_kab, nusrt', 'length', 'max'=>2),
			array('kode_kec, kode_desa', 'length', 'max'=>3),
			array('nbs', 'length', 'max'=>4),
			array('nama_krt, alamat', 'length', 'max'=>255),
			array('tipe_kues', 'length', 'max'=>1),
			// The following rule is used by search().
			// Please remove those attributes that should not be searched.
			array('kode_prov, kode_kab, kode_kec, kode_desa, nbs, nusrt, nama_krt, alamat, tipe_kues, kode_tanaman', 'safe', 'on'=>'search'),
		);
	}

	/**
	 * @return array relational rules.
	 */
	public function relations()
	{
		// NOTE: you may need to adjust the relation name and the related
		// class name for the relations automatically generated below.
		return array(
			'tRts' => array(self::HAS_MANY, 'TRt', 'kode_desa'),
			'tRts1' => array(self::HAS_MANY, 'TRt', 'kode_kab'),
			'tRts2' => array(self::HAS_MANY, 'TRt', 'kode_kec'),
			'tRts3' => array(self::HAS_MANY, 'TRt', 'kode_prov'),
			'tRts4' => array(self::HAS_MANY, 'TRt', 'nbs'),
			'tRts5' => array(self::HAS_MANY, 'TRt', 'nusrt'),
			'kodeDesa' => array(self::BELONGS_TO, 'MBs', 'kode_desa'),
			'kodeKab' => array(self::BELONGS_TO, 'MBs', 'kode_kab'),
			'kodeKec' => array(self::BELONGS_TO, 'MBs', 'kode_kec'),
			'kodeProv' => array(self::BELONGS_TO, 'MBs', 'kode_prov'),
			'nbs0' => array(self::BELONGS_TO, 'MBs', 'nbs'),
			'kodeTanaman' => array(self::BELONGS_TO, 'MTanaman', 'kode_tanaman'),
		);
	}

	/**
	 * @return array customized attribute labels (name=>label)
	 */
	public function attributeLabels()
	{
		return array(
			'kode_prov' => 'Kode Prov',
			'kode_kab' => 'Kode Kab',
			'kode_kec' => 'Kode Kec',
			'kode_desa' => 'Kode Desa',
			'nbs' => 'Nbs',
			'nusrt' => 'Nusrt',
			'nama_krt' => 'Nama Krt',
			'alamat' => 'Alamat',
			'tipe_kues' => 'Tipe Kues',
			'kode_tanaman' => 'Kode Tanaman',
		);
	}

	/**
	 * Retrieves a list of models based on the current search/filter conditions.
	 * @return CActiveDataProvider the data provider that can return the models based on the search/filter conditions.
	 */
	public function search()
	{
		// Warning: Please modify the following code to remove attributes that
		// should not be searched.

		$criteria=new CDbCriteria;

		$criteria->compare('kode_prov',$this->kode_prov,true);
		$criteria->compare('kode_kab',$this->kode_kab,true);
		$criteria->compare('kode_kec',$this->kode_kec,true);
		$criteria->compare('kode_desa',$this->kode_desa,true);
		$criteria->compare('nbs',$this->nbs,true);
		$criteria->compare('nusrt',$this->nusrt,true);
		$criteria->compare('nama_krt',$this->nama_krt,true);
		$criteria->compare('alamat',$this->alamat,true);
		$criteria->compare('tipe_kues',$this->tipe_kues,true);
		$criteria->compare('kode_tanaman',$this->kode_tanaman);

		return new CActiveDataProvider($this, array(
			'criteria'=>$criteria,
		));
	}
}